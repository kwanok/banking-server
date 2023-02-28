package com.numble.banking.friend.service

import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode
import com.numble.banking.friend.dsl.Friends
import com.numble.banking.friend.dto.FriendResponse
import com.numble.banking.user.User
import lombok.extern.slf4j.Slf4j
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
@Slf4j
class FriendService {
    fun create(user: User, friendId: Long) {
        transaction {
            if (user.id.value == friendId) {
                throw ApiException(ErrorCode.INVALID_FRIEND_REQUEST)
            }

            if (user.friends.any { it.id.value == friendId }) {
                throw ApiException(ErrorCode.ALREADY_FRIEND)
            }

            Friends.insert {
                it[sender] = user.id
                it[receiver] = friendId
            }
        }
    }

    fun get(user: User): List<FriendResponse> {
        return transaction {
            user.friends.with(User::accounts).map { FriendResponse(it, it.accounts.toList()) }
        }
    }
}