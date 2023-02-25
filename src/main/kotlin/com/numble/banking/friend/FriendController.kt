package com.numble.banking.friend

import com.numble.banking.friend.dto.FriendResponse
import com.numble.banking.friend.service.FriendService
import com.numble.banking.user.User
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("friends")
@Slf4j
class FriendController(
    val friendService: FriendService,
) {
    @PostMapping("/{friendId}")
    fun create(@AuthenticationPrincipal user: User, @PathVariable friendId: Long) {
        friendService.create(user, friendId)
    }

    @GetMapping
    fun get(@AuthenticationPrincipal user: User): ResponseEntity<List<FriendResponse>> {
        return ResponseEntity.ok(friendService.get(user))
    }

}