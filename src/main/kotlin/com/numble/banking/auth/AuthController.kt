package com.numble.banking.auth

import com.numble.banking.auth.dto.LoginRequest
import com.numble.banking.auth.dto.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/register")
    fun register(@Validated @RequestBody request: RegisterRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/login")
    fun login(@Validated @RequestBody request: LoginRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.login(request))
    }
}