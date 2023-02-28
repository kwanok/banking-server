package com.numble.banking.user.service

import com.numble.banking.user.User
import com.numble.banking.user.dto.UserResponse
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService {
    fun get(): List<UserResponse> {
        return transaction { User.all().with(User::accounts).toList().map {
            UserResponse(it, it.accounts.toList())
        }}
    }
}