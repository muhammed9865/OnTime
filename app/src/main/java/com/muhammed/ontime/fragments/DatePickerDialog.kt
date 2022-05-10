package com.muhammed.ontime.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muhammed.ontime.R
import com.muhammed.ontime.Utils
import com.muhammed.ontime.databinding.DatePickerDialogBinding
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class DatePickerDialog(private val mContext: Context) : BottomSheetDialogFragment() {

    private val binding: DatePickerDialogBinding by lazy {
        DatePickerDialogBinding.inflate(
            LayoutInflater.from(mContext)
        )
    }
    private lateinit var dpd: DatePickerDialog
    private var onDateChanged: ((millis: Long) -> Unit)? = null
    private var time = 0L
    private var date = 0L
    private var totalMillis = 0L

    init {
        initializeDatePicker(mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.datePickBtn.setOnClickListener {
            dpd.show()
        }

        onTimeChangedListener()

        return binding.root
    }


    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

    private fun initializeDatePicker(context: Context) {
        val cal = Calendar.getInstance()
        dpd = DatePickerDialog(
            context,
            { datePicker, p1, p2, p3 ->
                cal.apply {
                    set(Calendar.YEAR, datePicker.year)
                    set(Calendar.MONTH, datePicker.month)
                    set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
                    set(Calendar.HOUR, 0)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    updateDate(cal.timeInMillis)
                    onDateChanged?.let { it(totalMillis) }
                }
            },
            cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH]
        )
        dpd.datePicker.minDate = System.currentTimeMillis()

    }

    fun setOnDateChangedListener(onDateChange: (millis: Long) -> Unit) {
        this.onDateChanged = onDateChange
    }

    fun setMinimumDate(timeInMillis: Long) {
        dpd.datePicker.minDate = if (timeInMillis > 0) timeInMillis else System.currentTimeMillis()
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeInMillis
        binding.timePicker.apply {
            hour = cal[Calendar.HOUR_OF_DAY]
            minute = cal[Calendar.MINUTE]
        }
    }


    @OptIn(ExperimentalTime::class)
    private fun onTimeChangedListener() {
        binding.timePicker.setOnTimeChangedListener { timePicker, i, i2 ->
            val hours = Duration.convert(
                timePicker.hour.toDouble(),
                DurationUnit.HOURS,
                DurationUnit.MILLISECONDS
            )
            val minutes = Duration.convert(
                timePicker.minute.toDouble(),
                DurationUnit.MINUTES,
                DurationUnit.MILLISECONDS
            )
            updateTime((hours + minutes).toLong())
            onDateChanged?.let { it(totalMillis) }
        }
    }

    private fun updateTime(newTime: Long) {
        val prevTime = time
        totalMillis -= prevTime
        totalMillis += newTime
        time = newTime
        Log.d("DatePickerDialog", "updateTime: ${Utils.milliToDateAsString(newTime)}")
        updateUI()
    }

    private fun updateDate(newDate: Long) {
        val prevDate = date
        totalMillis -= prevDate
        totalMillis += newDate
        date = newDate
        updateUI()
    }

    private fun updateUI() {
        binding.finalDateText.text = Utils.milliToDateAsString(totalMillis)
    }
}