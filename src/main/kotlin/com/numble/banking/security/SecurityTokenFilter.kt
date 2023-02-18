package com.numble.banking.security

import com.numble.banking.user.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityTokenFilter(
    val securityTokenService: SecurityTokenService,
    val customUserDetailsService: CustomUserDetailsService,
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            val token = authorizationHeader.substring(7)
            println(token)
            val userEmail = securityTokenService.getUsername(token)
            println(userEmail)
            val userDetails = customUserDetailsService.loadUserByUsername(userEmail)
            println(userDetails)

            if (securityTokenService.isTokenValid(token, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authToken.details = userDetails
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }

}