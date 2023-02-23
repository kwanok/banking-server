package com.numble.banking.common

import com.numble.banking.user.User
import com.numble.banking.user.Users
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig{
    @Bean
    fun userDetailService(): UserDetailsService? {
        return UserDetailsService { email ->
            User.find { Users.email eq email }.firstOrNull()
                ?: throw UsernameNotFoundException("User not found")
        }
    }
    @Bean
    fun authenticationProvider(): AuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailService())
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}