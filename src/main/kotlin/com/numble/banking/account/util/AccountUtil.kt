package com.numble.banking.account.util

import org.joda.time.DateTime
import java.util.Random

class AccountUtil {
    companion object {
        fun generateAccountNumber(): String {
            val current = System.currentTimeMillis().toString().reversed().slice(0..4)
            val random = Random().nextInt(1000)
            val month = (DateTime.now().monthOfYear().get() * 7) % 100

            return "530-$current-$random-$month"
        }
    }
}