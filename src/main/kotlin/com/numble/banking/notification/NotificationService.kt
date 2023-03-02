package com.numble.banking.notification

import com.numble.banking.transfer.Transfer
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class NotificationService {
    fun sendNotification(transfer: Transfer) {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        transaction {
            println("Notification sent for transfer ${transfer.id}")
        }
    }
}