package com.numble.banking.user

import com.numble.banking.user.dto.UserResponse
import com.numble.banking.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController (
    val userService: UserService,
) {
    @GetMapping
    fun get(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.get())
    }
}