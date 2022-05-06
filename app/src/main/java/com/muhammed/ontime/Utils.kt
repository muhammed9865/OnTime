package com.muhammed.ontime

import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun milliToDateAsString(milli: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm:ss a", Locale.ENGLISH)
        val date = Date(milli)
        return sdf.format(date)
    }

    fun DatePicker.convertToString(): String = "$dayOfMonth $month-$year"

    fun AutoCompleteTextView.showDropdown(adapter: ArrayAdapter<String>?) {
        if (!TextUtils.isEmpty(this.text.toString())) {
            adapter?.filter?.filter(null)
        }
        setAdapter(adapter)
    }
}