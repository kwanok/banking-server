package com.numble.banking.auth

import com.numble.banking.auth.dto.AuthResponse
import com.numble.banking.auth.dto.LoginRequest
import com.numble.banking.auth.dto.RegisterRequest
import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode
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
    fun register(request: RegisterRequest): AuthResponse {
        return AuthResponse(
            token = securityTokenService.generateToken(
                transaction {
                    if (User.find { Users.email eq request.email }.count() > 0) {
                        throw ApiException(ErrorCode.EMAIL_ALREADY_EXISTS)
                    }

                    User.new {
                        name = request.name
                        email = request.email
                        password = passwordEncoder.encode(request.password)
                        expired = false
                        locked = false
                    }
                })
        )
    }

    fun login(request: LoginRequest): AuthResponse {
        println(request)

        val user = transaction {
            User.find { Users.email eq request.email }.firstOrNull()
        } ?: throw ApiException(ErrorCode.USER_NOT_FOUND)

        if (passwordEncoder.matches(request.password, user.password)) {
            return AuthResponse(
                token = securityTokenService.generateToken(user)
            )
        }

        throw ApiException(ErrorCode.PASSWORD_WRONG)
    }

}
