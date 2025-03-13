package com.kotlinexample.knews.ui.screens.common

import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun getTimeDifference(publishedAt: String): String {
    return try {
        // Parse the given ISO 8601 timestamp
        val publishedTime = ZonedDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)

        // Get current time in UTC
        val currentTime = ZonedDateTime.now()

        // Calculate the difference in hours and days
        val duration = Duration.between(publishedTime, currentTime)
        val hours = duration.toHours()
        val days = duration.toDays()

        // Return formatted result
        when {
            hours < 1 -> "Just now"
            hours in 1..23 -> "$hours hours ago"
            days == 1L -> "Yesterday"
            days > 1 -> "$days days ago"
            else -> "Unknown time"
        }
    } catch (e: Exception) {
        "Unknown time" // Handle parsing errors
    }
}