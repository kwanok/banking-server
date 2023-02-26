package com.numble.banking.transfer.dsl

import com.numble.banking.account.dsl.Accounts
import com.numble.banking.database.base.BaseTable

object Transfers : BaseTable() {
    val sender = reference("sender", Accounts)
    val receiver = reference("receiver", Accounts)
    val amount = decimal("amount", 10, 2)
    val description = varchar("description", 256)
    val status = enumeration("status", TransferStatus::class)
}