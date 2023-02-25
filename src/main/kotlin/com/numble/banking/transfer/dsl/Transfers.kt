package com.numble.banking.transfer.dsl

import com.numble.banking.account.dsl.Accounts
import org.jetbrains.exposed.dao.id.LongIdTable

object Transfers : LongIdTable() {
    val sender = reference("sender", Accounts)
    val receiver = reference("receiver", Accounts)
    val amount = decimal("amount", 10, 2)
    val description = varchar("description", 256)
    val status = enumeration("status", TransferStatus::class)
}