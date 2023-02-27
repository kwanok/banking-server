package com.numble.banking.transfer.dsl

import com.numble.banking.account.dsl.Accounts
import com.numble.banking.database.base.BaseTable

object Transfers : BaseTable() {
    val sender = reference("sender", Accounts)
    val receiver = reference("receiver", Accounts)
    val amount = decimal("amount", 10, 2)
    val memo = varchar("memo", 256).nullable()
    val status = enumeration("status", TransferStatus::class)
}