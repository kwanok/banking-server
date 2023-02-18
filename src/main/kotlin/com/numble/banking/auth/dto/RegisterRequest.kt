package com.numble.banking.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class RegisterRequest(
    @field:NotBlank(message = "이메일은 필수입니다.")
    @field:Email(
        regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = [Pattern.Flag.CASE_INSENSITIVE],
        message = "이메일 형식이 아닙니다."
    )
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    val password: String,
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,
)