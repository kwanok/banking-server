package com.numble.banking.transfer.dto

import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode

data class TransferCreateRequest (
    val sendAccountNumber : String,
    val receiveAccountNumber: String,
    val amount : Double,
    val memo : String? = null,
) {
    fun validate() {
        if (sendAccountNumber == receiveAccountNumber) {
            throw ApiException(ErrorCode.SENDER_RECEIVER_SAME)
        }
    }
}

