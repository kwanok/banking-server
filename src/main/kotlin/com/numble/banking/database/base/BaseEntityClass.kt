package com.numble.banking.database.base

import org.jetbrains.exposed.dao.EntityChangeType
import org.jetbrains.exposed.dao.EntityHook
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.toEntity
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

abstract class BaseEntityClass<E: BaseEntity>(table: BaseTable) : LongEntityClass<E>(table) {
    init {
        EntityHook.subscribe { entity ->
            if (entity.changeType == EntityChangeType.Updated) try {
                entity.toEntity(this)?.updatedAt = DateTime.now(DateTimeZone.UTC)
            } catch (e: Exception) {
                // ignore
            }
        }
    }
}