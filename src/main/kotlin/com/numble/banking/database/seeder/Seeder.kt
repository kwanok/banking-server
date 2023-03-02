package com.numble.banking.database.seeder

import com.numble.banking.account.Account
import com.numble.banking.account.util.AccountUtil
import com.numble.banking.friend.dsl.Friends
import com.numble.banking.user.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


class Seeder {
    fun seed() {
        val jinx = createUser("Jinx", emailGenerator("Jinx"))
        createAccount("Jinx's Account", jinx, 1000.0)

        val lux = createUser("Lux", emailGenerator("Lux"))
        createAccount("Lux's Account", lux, 7950000000.0)

        val ezreal = createUser("Ezreal", emailGenerator("Ezreal"))
        createAccount("Ezreal's Account", ezreal, 1000000.0)

        val sivir = createUser("Sivir", emailGenerator("Sivir"))
        createAccount("Sivir's Account", sivir, 3000000.0)

        sendFriend(jinx, lux)
        sendFriend(jinx, ezreal)
        sendFriend(jinx, sivir)

        sendFriend(lux, jinx)
        sendFriend(lux, ezreal)
    }

    private fun createUser(email: String, name: String): User {
        return User.new {
            this.name = name
            this.email = email
            password = "password"
        }
    }

    private fun createAccount(name: String, user: User, balance: Double): Account {
        return Account.new {
            this.name = name
            this.user = user
            this.balance = balance
            this.number = AccountUtil.generateAccountNumber()
        }
    }

    private fun emailGenerator(name: String): String {
        return name.lowercase(Locale.getDefault()) + "@numblebank.com"
    }

    private fun sendFriend(s: User, r: User) {
        transaction {
            Friends.insert {
                it[sender] = s.id
                it[receiver] = r.id
            }
        }
    }
}