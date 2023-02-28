package com.numble.banking.mock

import com.numble.banking.user.User
import org.jetbrains.exposed.sql.transactions.transaction

class UserFixture {
    companion object {
        fun createUser(): User {
            return transaction {
                User.new {
                    name = "test"
                    email = "test@example.com"
                    password = "password"
                }
            }
        }
    }
}