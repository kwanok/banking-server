package com.numble.banking.friend.dsl

import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.sql.Table

object Friends : Table() {
    val sender = reference("sender", Users)
    val receiver = reference("receiver", Users)
}