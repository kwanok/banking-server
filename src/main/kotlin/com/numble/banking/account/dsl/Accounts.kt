package com.numble.banking.account.dsl

import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
object Accounts : LongIdTable() {
    val name: Column<String> = varchar("name", 50)
    val balance: Column<Double> = double("balance")
    val user: Column<EntityID<Long>> = reference("user", Users)
}