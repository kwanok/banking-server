package com.numble.banking.database.base

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
abstract class BaseTable : LongIdTable() {
    val createdAt = datetime("created_at").clientDefault {
        DateTime.now(DateTimeZone.UTC)
    }
    val updatedAt = datetime("updated_at").nullable().clientDefault {
        DateTime.now(DateTimeZone.UTC)
    }
}