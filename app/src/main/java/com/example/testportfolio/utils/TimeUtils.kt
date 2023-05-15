package com.example.testportfolio.utils

import java.sql.Date
import java.sql.Timestamp
import java.util.Locale

fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null ) return ""
    val stamp = Date(Timestamp(timestamp * 1000).time)
    val sdf = java.text.SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    sdf.timeZone = java.util.TimeZone.getDefault()
    return sdf.format(stamp)
}