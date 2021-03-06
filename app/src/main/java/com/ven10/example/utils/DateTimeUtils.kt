package com.ven10.example.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun formatDate( date: String): String{
        return SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
                .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(date))
    }

    fun toTimeStamp(date: String): Date? {
        return SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).parse(date)
    }

    fun toTimeStampRelative(date: String): CharSequence? {
        return DateUtils.getRelativeTimeSpanString(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(date).time)
    }


    fun formatTime(time: String):String{
        return SimpleDateFormat("h:mm a", Locale.getDefault())
                .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(time))
    }
}