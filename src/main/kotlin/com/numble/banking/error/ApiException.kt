package com.numble.banking.error

class ApiException(
    val code: ErrorCode,
    val details: String,
) : RuntimeException() {
    constructor(code: ErrorCode) : this(code, "")
}