package com.numble.banking

import com.numble.banking.database.DB
import org.jetbrains.exposed.sql.Database
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankingApplication

fun main(args: Array<String>) {
    Database.connect(DB.db)

    runApplication<BankingApplication>(*args)
}
