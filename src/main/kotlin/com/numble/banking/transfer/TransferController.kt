package com.numble.banking.transfer

import com.numble.banking.account.Account
import com.numble.banking.transfer.dto.TransferCreateRequest
import com.numble.banking.transfer.service.TransferService
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
    fun create(@RequestBody request: TransferCreateRequest) {
        transferService.transfer(request)
    }
}