package com.numble.banking.user.dto

import com.numble.banking.account.Account
import com.numble.banking.account.dto.AccountResponse
import com.numble.banking.user.User

data class UserResponse(
    val id: Long,
    val name: String,
    val accounts: List<AccountResponse>,
) {
    constructor (user: User, accounts: List<Account>) : this(user.id.value, user.name, accounts.map { AccountResponse(it) } )
}