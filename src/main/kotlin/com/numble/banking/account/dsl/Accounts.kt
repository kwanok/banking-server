package com.numble.banking.account.dsl

import com.numble.banking.database.base.BaseTable
import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
object Accounts : BaseTable() {
    val name: Column<String> = varchar("name", 50)
    val balance: Column<Double> = double("balance")
    val user: Column<EntityID<Long>> = reference("user", Users)
}