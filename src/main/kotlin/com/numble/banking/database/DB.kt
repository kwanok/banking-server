package com.numble.banking.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
data class DB(
    var url: String = "",
    var username: String = "",
    var password: String = "",
    var driverClassName: String = "",
) {
    @Bean
    fun db() {
        Database.connect(url, driver = driverClassName, user = username, password = password)
        transaction {
            Schema().init()
        }
    }
}