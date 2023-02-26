package com.numble.banking.account

import com.numble.banking.account.dsl.Accounts
import com.numble.banking.database.base.BaseEntity
import com.numble.banking.database.base.BaseEntityClass
import com.numble.banking.user.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Account(id: EntityID<Long>) : BaseEntity(id, Accounts) {
    companion object : BaseEntityClass<Account>(Accounts)

    var name by Accounts.name
    var balance by Accounts.balance
    var user by User referencedOn Accounts.user
}