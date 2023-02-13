package com.numble.banking.auth

import com.numble.banking.auth.dto.LoginRequest
import com.numble.banking.auth.dto.RegisterRequest
import com.numble.banking.security.SecurityTokenService
import com.numble.banking.user.User
import com.numble.banking.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    var userRepository: UserRepository,
    var securityTokenService: SecurityTokenService,
    var passwordEncoder: PasswordEncoder,
) {
    fun register(request: RegisterRequest): String {
        val user = userRepository.save(
            User(
                email = request.email,
                name = request.name,
                password = passwordEncoder.encode(request.password)
            )
        )
        return securityTokenService.generateToken(user)
    }

    fun login(request: LoginRequest): String {
        val user = userRepository.findByEmail(request.email)!!
        if (passwordEncoder.matches(request.password, user.password)) {
            return securityTokenService.generateToken(user)
        }
        throw Exception("Invalid credentials")
    }

}
