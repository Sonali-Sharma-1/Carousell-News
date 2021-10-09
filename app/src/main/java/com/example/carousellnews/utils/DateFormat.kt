package com.example.carousellnews.utils

import java.util.*

class DateFormat {
    companion object {
        private fun currentDate(): Date {
            val calendar = Calendar.getInstance()
            return calendar.time
        }

        @JvmStatic
        fun getTimeAgo(timeInMillis: Int): String {
            val SECOND_MILLIS = 1000
            val MINUTE_MILLIS = 60 * SECOND_MILLIS
            val HOUR_MILLIS = 60 * MINUTE_MILLIS
            val DAY_MILLIS = 24 * HOUR_MILLIS

            var time = timeInMillis
            if (time < 1000000000000L) {
                time *= 1000
            }

            val now = currentDate().time
            if (time > now || time <= 0) {
                return "1 month ago"
            }

            val diff = now - time
            return when {
                diff < MINUTE_MILLIS -> "moments ago"
                diff < 2 * MINUTE_MILLIS -> "a minute ago"
                diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
                diff < 2 * HOUR_MILLIS -> "an hour ago"
                diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
                diff < 48 * HOUR_MILLIS -> "yesterday"
                else -> "${diff / DAY_MILLIS} days ago"
            }
        }
    }
}