package com.numble.banking.account

import com.numble.banking.account.dsl.Accounts
import com.numble.banking.user.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Account(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Account>(Accounts)

    var name by Accounts.name
    var balance by Accounts.balance
    var user by User referencedOn Accounts.user
}