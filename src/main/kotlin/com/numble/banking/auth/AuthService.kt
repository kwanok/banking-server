package com.numble.banking.auth

import com.numble.banking.auth.dto.LoginRequest
import com.numble.banking.auth.dto.RegisterRequest
import com.numble.banking.security.SecurityTokenService
import com.numble.banking.user.User
import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    var securityTokenService: SecurityTokenService,
    var passwordEncoder: PasswordEncoder,
) {
    fun register(request: RegisterRequest): String {
        return securityTokenService.generateToken(
            transaction {
                if (User.find { Users.email eq request.email }.count() > 0) {
                    throw Exception("User already exists")
                }

                User.new {
                    name = request.name
                    email = request.email
                    password = passwordEncoder.encode(request.password)
                    expired = false
                    locked = false
                }
            }
        )
    }

    fun login(request: LoginRequest): String {
        val user = User.find { Users.email eq request.email }.first()

        if (passwordEncoder.matches(request.password, user.password)) {
            return securityTokenService.generateToken(user)
        }

        throw Exception("Invalid credentials")
    }

}
