package com.example.spacelaunchnow.common

import java.text.SimpleDateFormat
import java.util.Locale


fun String.toDate(): String {
    val inputFormats = arrayOf(
        "yyyy-MM-dd",
        "yyyy-MM-dd'T'HH:mm:ssXXX"
    )
    val outputFormat = "dd/MM/yyyy"
    for (format in inputFormats) {
        try {
            val date = SimpleDateFormat(format, Locale.US).parse(this)
            return SimpleDateFormat(outputFormat, Locale.US).format(date)
        } catch (e: Exception) {
            // Ignore and try the next format
        }
    }
    return "-"
}
