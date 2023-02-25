package com.numble.banking

import com.numble.banking.database.DB
import com.numble.banking.database.Schema
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankingApplication

fun main(args: Array<String>) {
    Database.connect(DB.db)
    transaction {
        Schema().init()
    }

    runApplication<BankingApplication>(*args)
}
