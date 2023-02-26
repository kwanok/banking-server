package com.numble.banking.friend.dsl

import com.numble.banking.database.base.BaseTable
import com.numble.banking.user.dsl.Users

object Friends : BaseTable() {
    val sender = reference("sender", Users)
    val receiver = reference("receiver", Users)
}