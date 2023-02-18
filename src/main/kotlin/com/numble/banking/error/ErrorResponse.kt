package com.numble.banking.error

data class ErrorResponse(
    var code: String? = null,
    var message: String? = null,
    var details: Any? = null,
) {
    fun json(): String {
        return "{\"code\": \"$code\", \"message\": \"$message\", \"details\": \"$details\"}"
    }
}