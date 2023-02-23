package com.numble.banking.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource
object DB {
    val db: DataSource = connect()

    private fun connect(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:mysql://localhost:3306/banking-db"
        config.username = "db_user"
        config.password = "eZkRv7nG786pcyr3"
        config.driverClassName = "com.mysql.cj.jdbc.Driver"

        return HikariDataSource(config)
    }
}