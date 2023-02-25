package com.numble.banking.account

import com.numble.banking.account.dto.AccountCreateRequest
import com.numble.banking.account.dto.AccountResponse
import com.numble.banking.account.service.AccountService
import com.numble.banking.user.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accounts")
class AccountController(
    val accountService: AccountService,
) {
    @PostMapping
    fun create(@AuthenticationPrincipal user: User, @RequestBody request: AccountCreateRequest): ResponseEntity<Void> {
        return accountService.create(request, user)
    }

    @GetMapping
    fun get(@AuthenticationPrincipal user: User): ResponseEntity<List<AccountResponse>> {
        return accountService.get(user)
    }

    @GetMapping("/{id}")
    fun find(@AuthenticationPrincipal user: User, @PathVariable id: Long): ResponseEntity<AccountResponse> {
        return accountService.find(user, id)
    }
}
