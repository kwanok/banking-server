package com.numble.banking.user

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun testNewUser() {
        val user = User(
            id = 1,
            name = "Test User",
            email = "test@example.com",
            password = "password",
        )

        assertEquals("password", user.password)
    }
}