package com.numble.banking.error

enum class ErrorCode(
    var status: Int,
    var message: String,
) {
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    // Auth
    ACCESS_DENIED(403, "Access denied"),
    UNAUTHORIZED(401, "Unauthorized"),

    USER_NOT_FOUND(404, "User not found"),;


}