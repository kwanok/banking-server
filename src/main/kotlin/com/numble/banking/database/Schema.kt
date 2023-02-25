package com.numble.banking.database

import com.numble.banking.account.dsl.Accounts
import com.numble.banking.friend.dsl.Friends
import com.numble.banking.transfer.dsl.Transfers
import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.sql.SchemaUtils

class Schema {
    fun init() {
        SchemaUtils.createMissingTablesAndColumns(
            Users,
            Accounts,
            Friends,
            Transfers,
        )
    }
}