package com.numble.banking.transfer

import com.numble.banking.transfer.dto.TransferCreateRequest
import com.numble.banking.transfer.service.TransferService
import com.numble.banking.user.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transfers")
class TransferController(
    val transferService: TransferService,
) {
    @PostMapping
    fun create(
        @AuthenticationPrincipal user: User,
        @RequestBody request: TransferCreateRequest
    ) {
        transferService.transfer(user, request)
    }
}