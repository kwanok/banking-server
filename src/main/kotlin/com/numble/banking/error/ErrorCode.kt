package com.numble.banking.error

enum class ErrorCode(
    var status: Int,
    var message: String,
) {
    INTERNAL_SERVER_ERROR(500, "Internal server error"),

    // Auth
    ACCESS_DENIED(403, "Access denied"),
    UNAUTHORIZED(401, "Unauthorized"),

    USER_NOT_FOUND(404, "User not found"),
    ACCOUNT_NOT_FOUND(404, "Account not found"),

    SENDER_RECEIVER_SAME(400, "Sender and receiver are the same"),
    INSUFFICIENT_BALANCE(400, "Insufficient balance"),
    ACCOUNT_LIMIT_EXCEEDED(400, "Account limit exceeded"),

    ALREADY_FRIEND(409, "Already friend"),
    INVALID_FRIEND_REQUEST(400, "Invalid friend request"),

    NOT_FRIEND(403, "Not friend")
    ;


}