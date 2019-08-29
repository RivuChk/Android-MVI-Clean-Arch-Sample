package dev.rivu.nasaapodarchive.domain.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.daysAgo(daysAgo: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    return calendar.time
}

fun Date.format(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("PST")
    return simpleDateFormat.format(this)
}

fun String.parseDate(): Date {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("PST")
    return simpleDateFormat.parse(this)
}