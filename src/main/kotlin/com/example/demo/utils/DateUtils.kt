package com.example.demo.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class DateUtils {
    companion object {
        fun getCurrentDate(): Date? {
            val calendar = Calendar.getInstance()
            return calendar.time
        }
    }
}