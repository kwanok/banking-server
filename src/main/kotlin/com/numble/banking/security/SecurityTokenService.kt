package com.numble.banking.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function

@Service
class SecurityTokenService {
    fun generateToken(userDetails: UserDetails): String {
        val claims = Jwts.claims().setSubject(userDetails.username)
        claims["scopes"] = userDetails.authorities
        return Jwts.builder()
            .setClaims(claims)
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims?, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    fun getUsername(token: String): String {
        return extractClaim(token) { obj: Claims? -> obj!!.subject }
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun isTokenValid(token: String?, userDetails: UserDetails): Boolean {
        val username = getUsername(token!!)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun extractAllClaims(token: String): Claims? {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { obj: Claims? -> obj!!.expiration }
    }

    private fun getSignInKey(): Key? {
        val keyBytes = Decoders.BASE64.decode("SECRETKESECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYY")
        return Keys.hmacShaKeyFor(keyBytes)
    }
}