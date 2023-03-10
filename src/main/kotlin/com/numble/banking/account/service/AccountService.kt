package com.numble.banking.account.service

import com.numble.banking.account.Account
import com.numble.banking.account.dsl.Accounts
import com.numble.banking.account.dto.AccountCreateRequest
import com.numble.banking.account.dto.AccountResponse
import com.numble.banking.account.util.AccountUtil
import com.numble.banking.error.ApiException
import com.numble.banking.error.ErrorCode
import com.numble.banking.user.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountService {
    fun create(request: AccountCreateRequest, user: User): ResponseEntity<Void> {
        transaction {
            val accountCount = Account.find { Accounts.user eq user.id }.count()
            if (accountCount >= 5) {
                throw ApiException(ErrorCode.ACCOUNT_LIMIT_EXCEEDED)
            }

            Account.new {
                name = request.name
                balance = 0.0
                number = AccountUtil.generateAccountNumber()
                this.user = user
            }
        }

        return ResponseEntity.ok().build()
    }

    fun get(user: User): ResponseEntity<List<AccountResponse>> {
        val accounts = transaction {
            Account.find { Accounts.user eq user.id }.map {
                AccountResponse(it)
            }
        }

        return ResponseEntity.ok(accounts)
    }

    fun find(user: User, id: Long): ResponseEntity<AccountResponse> {
        val account = transaction {
            Account.find { (Accounts.user eq user.id) and (Accounts.id eq id) }.firstOrNull()
        }

        return if (account != null) {
            ResponseEntity.ok(AccountResponse(account))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}