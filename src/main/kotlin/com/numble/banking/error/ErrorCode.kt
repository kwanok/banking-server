package com.numble.banking.error

enum class ErrorCode(
    var status: Int,
    var message: String,
) {
    // Auth
    ACCESS_DENIED(403, "Access denied"),
    UNAUTHORIZED(401, "Unauthorized"),

}