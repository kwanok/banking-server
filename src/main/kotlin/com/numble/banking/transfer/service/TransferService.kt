package com.numble.banking.transfer.service

import com.numble.banking.account.Account
import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode
import com.numble.banking.transfer.Transfer
import com.numble.banking.transfer.dsl.TransferStatus
import com.numble.banking.transfer.dto.TransferCreateRequest
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class TransferService {
    fun transfer(
        request: TransferCreateRequest,
    ) {
        transaction {
            val sender = Account.findById(request.senderId) ?: throw ApiException(ErrorCode.USER_NOT_FOUND)
            val receiver = Account.findById(request.receiverId) ?: throw ApiException(ErrorCode.USER_NOT_FOUND)

            Transfer.new {
                this.sender = sender.id
                this.receiver = receiver.id
                this.amount = request.amount.toBigDecimal()
                this.description = "Transfer"
                this.status = TransferStatus.PENDING
            }
        }
    }
}