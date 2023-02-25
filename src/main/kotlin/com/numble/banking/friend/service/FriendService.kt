package com.numble.banking.friend.service

import com.numble.banking.friend.dsl.Friends
import com.numble.banking.friend.dto.FriendResponse
import com.numble.banking.user.User
import lombok.extern.slf4j.Slf4j
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
@Slf4j
class FriendService {
    fun create(user: User, friendId: Long) {
        transaction {
            Friends.insert {
                it[sender] = user.id
                it[receiver] = friendId
            }
        }
    }

    fun get(user: User): List<FriendResponse> {
        return transaction {
            user.friends.map { FriendResponse(it) }
        }
    }
}