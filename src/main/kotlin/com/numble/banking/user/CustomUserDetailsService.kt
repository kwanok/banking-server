package com.numble.banking.user

import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return transaction { User.find { Users.email eq username }.firstOrNull() ?: throw Exception("User not found")}
    }
}