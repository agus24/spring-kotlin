package com.example.demo.utils

import java.util.*

class DateUtils {
    companion object {
        fun getCurrentDate(): Date? {
            return Calendar.getInstance().time
        }
    }
}