package com.numble.banking.transfer

import com.numble.banking.database.base.BaseEntity
import com.numble.banking.database.base.BaseEntityClass
import com.numble.banking.transfer.dsl.Transfers
import org.jetbrains.exposed.dao.id.EntityID

class Transfer(id: EntityID<Long>) : BaseEntity(id, Transfers) {
    companion object : BaseEntityClass<Transfer>(Transfers)

    var sender by Transfers.sender
    var receiver by Transfers.receiver
    var amount by Transfers.amount
    var memo by Transfers.memo
    var status by Transfers.status
}