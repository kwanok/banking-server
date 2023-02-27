package com.numble.banking.account.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class AccountCreateRequest(
    @field:NotBlank
    @field:Length(
        max = 50,
        message = "계좌 이름은 50자를 초과할 수 없습니다."
    )
    @field:Pattern(
        regexp = "^[a-zA-Z0-9가-힣 ]*\$",
        message = "계좌 이름은 영문, 숫자, 한글만 사용할 수 있습니다."
    )
    val name: String,
)
