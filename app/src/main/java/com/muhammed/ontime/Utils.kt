package com.muhammed.ontime

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun milliToDateAsString(milli: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm:ss a", Locale.ENGLISH)
        val date = Date(milli)
        return sdf.format(date)
    }
}