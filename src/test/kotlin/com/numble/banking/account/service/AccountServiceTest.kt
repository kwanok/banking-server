package com.numble.banking.account.service

import com.numble.banking.account.dto.AccountCreateRequest
import com.numble.banking.database.Schema
import com.numble.banking.mock.AccountFixture
import com.numble.banking.mock.UserFixture
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {
    private val accountService: AccountService = AccountService()

    @BeforeEach
    fun setup() {
    }

    @AfterEach
    fun tearDown() {
        transaction {
            Schema().drop()
        }
    }

    @Test
    fun `create account`() {
        val user = UserFixture.createUser()

        accountService.create(AccountCreateRequest("test"), user)

        val account = transaction {
            user.accounts.first()
        }

        assertEquals("test", account.name)
    }

    @Test
    fun `get account`() {
        val user = UserFixture.createUser()
        val account = AccountFixture.createAccount(user = user)

        val result = accountService.get(user)

        assertEquals(account.id.value, result.body?.firstOrNull()?.id)

        AccountFixture.createAccount()
        AccountFixture.createAccount()

        val result2 = accountService.get(user)

        assertEquals(1, result2.body?.size)
    }

}