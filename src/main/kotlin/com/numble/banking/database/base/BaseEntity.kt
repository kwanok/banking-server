package com.numble.banking.database.base

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class BaseEntity(id: EntityID<Long>, table: BaseTable) : LongEntity(id) {
    var createdAt by table.createdAt
    var updatedAt by table.updatedAt
}