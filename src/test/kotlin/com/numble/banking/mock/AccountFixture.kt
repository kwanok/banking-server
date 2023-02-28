package com.numble.banking.mock

import com.numble.banking.account.Account
import com.numble.banking.user.User
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

class AccountFixture {
    companion object {
        fun createAccount(
            name: String = "test",
            balance: Double = 1000.0,
            number: String = Random.nextInt(10000, 99999).toString(),
            user: User = UserFixture.createUser()
        ): Account {
            return transaction {
                Account.new {
                    this.name = name
                    this.balance = balance
                    this.number = number
                    this.user = user
                }
            }
        }
    }
}