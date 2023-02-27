package com.numble.banking.transfer.dto

import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode

data class TransferCreateRequest (
    val senderId : Long,
    val receiverId : Long,
    val amount : Double,
    val memo : String? = null,
) {
    fun validate() {
        if (senderId == receiverId) {
            throw ApiException(ErrorCode.SENDER_RECEIVER_SAME)
        }
    }
}

