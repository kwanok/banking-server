package com.numble.banking.user

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Users : LongIdTable() {
    val name: Column<String> = varchar("name", 50)
    val email: Column<String> = varchar("email", 50)
    val password: Column<String> = varchar("password", 256)
    val expired: Column<Boolean> = bool("expired").default(false)
    val locked: Column<Boolean> = bool("locked").default(false)
}