package com.numble.banking.friend.dto

import com.numble.banking.account.Account
import com.numble.banking.account.dto.AccountResponse
import com.numble.banking.user.User

data class FriendResponse(
    val id : Long,
    val name : String,
    val email : String,
    val accounts : List<AccountResponse>,
) {
    constructor(user: User, accounts: List<Account>) : this(user.id.value, user.name, user.email, accounts.map { AccountResponse(it) })
}
