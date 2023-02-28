package com.numble.banking.account

import com.numble.banking.database.Schema
import com.numble.banking.mock.AccountFixture.Companion.createAccount
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class AccountTests {
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
    fun `test Account 만들기`() {
        val account = transaction {
            createAccount()
        }

        assertEquals("test", account.name)
    }
}