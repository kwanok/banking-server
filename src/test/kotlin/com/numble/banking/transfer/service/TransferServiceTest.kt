package com.numble.banking.transfer.service

import com.numble.banking.account.Account
import com.numble.banking.database.Schema
import com.numble.banking.error.ApiException
import com.numble.banking.friend.dsl.Friends
import com.numble.banking.mock.AccountFixture
import com.numble.banking.notification.NotificationService
import com.numble.banking.transfer.dto.TransferCreateRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class TransferServiceTest {
    private val transferService: TransferService = TransferService(
        NotificationService()
    )

    @BeforeEach
    fun setUp() {
        transaction {
            Schema().init()
        }
    }

    @AfterEach
    fun tearDown() {
        transaction {
            Schema().drop()
        }
    }

    @Test
    fun transfer() {
        val accountA = AccountFixture.createAccount()
        val accountB = AccountFixture.createAccount()

        transaction {
            Friends.insert {
                it[sender] = accountA.user.id
                it[receiver] = accountB.user.id
            }

            accountA.balance = 10000.0
            accountB.balance = 10000.0
        }

        val userA = transaction {
            accountA.user
        }

        transferService.transfer(
            userA, TransferCreateRequest(
                accountA.number,
                accountB.number,
                1000.0
            )
        )

        transaction {
            assertEquals(9000.0, Account.findById(accountA.id.value)!!.balance)
            assertEquals(11000.0, Account.findById(accountB.id.value)!!.balance)
        }
    }

    @Test
    fun `transfer with insufficient balance`() {
        val accountA = AccountFixture.createAccount()
        val accountB = AccountFixture.createAccount()

        transaction {
            Friends.insert {
                it[sender] = accountA.user.id
                it[receiver] = accountB.user.id
            }

            accountA.balance = 10000.0
            accountB.balance = 10000.0
        }

        val userA = transaction {
            accountA.user
        }

        try {
            transferService.transfer(
                userA, TransferCreateRequest(
                    accountA.number,
                    accountB.number,
                    100000.0
                )
            )
        } catch (e: ApiException) {
            assertEquals(400, e.code.status)
            assertEquals("Insufficient balance", e.code.message)
        }

        transaction {
            assertEquals(10000.0, Account.findById(accountA.id.value)!!.balance)
            assertEquals(10000.0, Account.findById(accountB.id.value)!!.balance)
        }
    }

    @Test
    fun `transfer from invalid account`() {
        val accountA = AccountFixture.createAccount()
        val accountB = AccountFixture.createAccount()

        transaction {
            Friends.insert {
                it[sender] = accountA.user.id
                it[receiver] = accountB.user.id
            }

            accountA.balance = 10000.0
            accountB.balance = 10000.0
        }

        val userA = transaction {
            accountA.user
        }

        try {
            transferService.transfer(
                userA, TransferCreateRequest(
                    "invalid",
                    accountB.number,
                    1000.0
                )
            )
        } catch (e: ApiException) {
            assertEquals(404, e.code.status)
            assertEquals("Account not found", e.code.message)
        }

        transaction {
            assertEquals(10000.0, Account.findById(accountA.id.value)!!.balance)
            assertEquals(10000.0, Account.findById(accountB.id.value)!!.balance)
        }
    }

    @Test
    fun `transfer from account is not mine`() {
        val accountA = AccountFixture.createAccount()
        val accountB = AccountFixture.createAccount()

        transaction {
            Friends.insert {
                it[sender] = accountA.user.id
                it[receiver] = accountB.user.id
            }

            accountA.balance = 10000.0
            accountB.balance = 10000.0
        }

        val userA = transaction {
            accountA.user
        }

        try {
            transferService.transfer(
                userA, TransferCreateRequest(
                    accountB.number,
                    accountA.number,
                    1000.0
                )
            )
        } catch (e: ApiException) {
            assertEquals(403, e.code.status)
            assertEquals("Access denied", e.code.message)
        }

        transaction {
            assertEquals(10000.0, Account.findById(accountA.id.value)!!.balance)
            assertEquals(10000.0, Account.findById(accountB.id.value)!!.balance)
        }
    }

    @Test
    fun `transfer to not friend`() {
        val accountA = AccountFixture.createAccount()
        val accountB = AccountFixture.createAccount()

        transaction {
            accountA.balance = 10000.0
            accountB.balance = 10000.0
        }

        val userA = transaction {
            accountA.user
        }

        try {
            transferService.transfer(
                userA, TransferCreateRequest(
                    accountA.number,
                    accountB.number,
                    1000.0
                )
            )
        } catch (e: ApiException) {
            assertEquals(403, e.code.status)
            assertEquals("Not friend", e.code.message)
        }

        transaction {
            assertEquals(10000.0, Account.findById(accountA.id.value)!!.balance)
            assertEquals(10000.0, Account.findById(accountB.id.value)!!.balance)
        }
    }
}