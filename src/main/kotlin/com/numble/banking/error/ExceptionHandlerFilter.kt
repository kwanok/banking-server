package com.numble.banking.error

import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Slf4j
class ExceptionHandlerFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        }
        catch (e: SignatureException) {
            logger.error("Internal server error", e)
            response.contentType = "application/json;charset=UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(
                ErrorResponse(
                    ErrorCode.UNAUTHORIZED.name,
                    "Invalid JWT token",
                ).json()
            )
        }
        catch (e: MalformedJwtException) {
            logger.error("MalformedJwtException", e)
            response.contentType = "application/json;charset=UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(
                ErrorResponse(
                    ErrorCode.UNAUTHORIZED.name,
                    "Invalid JWT token",
                ).json()
            )
        }
        catch (e: Exception) {
            logger.error("Internal server error", e)
            response.contentType = "application/json;charset=UTF-8"
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write(
                ErrorResponse(
                    ErrorCode.INTERNAL_SERVER_ERROR.name,
                    "Internal server error",
                ).json()
            )
        }
    }
}