package com.numble.banking.account.dto

import com.numble.banking.account.Account

data class AccountResponse (
    val id: Long,
    val name: String,
    val balance: Double,
) {
    constructor(account: Account) : this(account.id.value, account.name, account.balance)
}