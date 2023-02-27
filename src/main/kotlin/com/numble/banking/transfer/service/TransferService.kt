package com.numble.banking.transfer.service

import com.numble.banking.account.Account
import com.numble.banking.account.dsl.Accounts
import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode
import com.numble.banking.transfer.Transfer
import com.numble.banking.transfer.dsl.TransferStatus
import com.numble.banking.transfer.dto.TransferCreateRequest
import com.numble.banking.user.User
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class TransferService {
    fun transfer(
        user: User,
        request: TransferCreateRequest,
    ) {
        request.validate()

        val account = transaction {
            Account.find { Accounts.number eq request.sendAccountNumber }.firstOrNull()
        } ?: throw ApiException(ErrorCode.ACCOUNT_NOT_FOUND)

        if (transaction { account.user }.id != user.id) {
            throw ApiException(ErrorCode.ACCESS_DENIED)
        }

        if (transaction { account.balance } < request.amount) {
            throw ApiException(ErrorCode.INSUFFICIENT_BALANCE)
        }

        transaction {
            val accounts =
                Account.find { (Accounts.number eq request.sendAccountNumber) or (Accounts.number eq request.receiveAccountNumber) }
                    .toList()
            if (accounts.size != 2) {
                throw ApiException(ErrorCode.USER_NOT_FOUND)
            }

            val sender = accounts.find { it.number == request.sendAccountNumber }!!
            val receiver = accounts.find { it.number == request.receiveAccountNumber }!!

            Transfer.new {
                this.sender = sender.id
                this.receiver = receiver.id
                this.amount = request.amount.toBigDecimal()
                this.memo = request.memo
                this.status = TransferStatus.SUCCESS
            }

            sender.balance -= request.amount
            receiver.balance += request.amount
        }
    }
}