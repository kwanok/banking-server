package com.numble.banking.user

import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun testNewUser() {
        transaction {
            SchemaUtils.create(Users)

            val user = User.new {
                email = "test@example.com"
                password = "password"
                name = "Test User"
            }

            assertEquals("password", user.getPassword())
        }

    }
}