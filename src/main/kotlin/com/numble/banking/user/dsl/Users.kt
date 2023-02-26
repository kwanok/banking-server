package com.numble.banking.user.dsl

import com.numble.banking.database.base.BaseTable
import org.jetbrains.exposed.sql.Column

object Users : BaseTable() {
    val name: Column<String> = varchar("name", 50).index()
    val email: Column<String> = varchar("email", 50).index()
    val password: Column<String> = varchar("password", 256)
    val expired: Column<Boolean> = bool("expired").default(false)
    val locked: Column<Boolean> = bool("locked").default(false)
}