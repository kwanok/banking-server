package com.numble.banking.friend.dto

import com.numble.banking.user.User

data class FriendResponse(
    val id : Long,
    val name : String,
    val email : String,
) {
    constructor(user: User) : this(user.id.value, user.name, user.email)
}
