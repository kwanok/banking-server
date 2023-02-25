package com.numble.banking.transfer.dto

data class TransferCreateRequest (
    val senderId : Long,
    val receiverId : Long,
    val amount : Double,
)

