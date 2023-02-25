package com.numble.banking.transfer

import com.numble.banking.transfer.dsl.Transfers
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Transfer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Transfer>(Transfers)

    var sender by Transfers.sender
    var receiver by Transfers.receiver
    var amount by Transfers.amount
    var description by Transfers.description
    var status by Transfers.status
}