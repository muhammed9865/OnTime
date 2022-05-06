package com.muhammed.ontime.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkManager
import com.muhammed.ontime.R
import com.muhammed.ontime.Utils
import com.muhammed.ontime.Utils.showDropdown
import com.muhammed.ontime.databinding.ActivityScheduleDetailsBinding
import com.muhammed.ontime.fragments.DatePickerDialog
import com.muhammed.ontime.viewmodel.ScheduleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleDetailsActivity : AppCompatActivity() {
    private val binding: ActivityScheduleDetailsBinding by lazy {
        ActivityScheduleDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: ScheduleDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.scheduleToolbar.setNavigationOnClickListener { onBackPressed() }

        with(binding) {
            scheduleTitle.doOnTextChanged { text, _, _, _ ->
                viewModel.setTitle(text.toString())
            }

            scheduleNote.doOnTextChanged { text, _, _, _ ->
                viewModel.setNote(text.toString())
            }

            schedulePlace.doOnTextChanged { text, _, _, _ ->
                viewModel.setPlace(text.toString())
            }

            saveSchedule.setOnClickListener {
                viewModel.saveSchedule(WorkManager.getInstance(this@ScheduleDetailsActivity))
                finish()
            }

            isFulldaySwitch.setOnCheckedChangeListener { _, _ ->
                viewModel.toggleFullDay()
            }

            startFrom.setOnClickListener {
                val dateDialog = DatePickerDialog()
                with(dateDialog) {
                    setOnDateChangedListener {
                        binding.startFrom.text = Utils.milliToDateAsString(it)
                        viewModel.setStartFrom(it)
                    }
                    show(supportFragmentManager, null)
                }
            }
            finish.setOnClickListener {
                val dateDialog = DatePickerDialog()
                with(dateDialog) {
                    setOnDateChangedListener {
                        binding.finish.text = Utils.milliToDateAsString(it)
                        viewModel.setFinish(it)

                    }
                    show(supportFragmentManager, null)
                }
            }
        }
        prepareReminderMenu()
    }

    override fun onResume() {
        super.onResume()
        displayScheduleDetails()
    }

    private fun prepareReminderMenu() {
        val options = resources.getStringArray(R.array.reminders)
        val arrAdapter = ArrayAdapter(this, R.layout.dropdown_item, options)
        binding.reminderOptions.showDropdown(arrAdapter)
        binding.reminderOptions.doOnTextChanged { text, _, _, _ ->
            viewModel.setReminder(text.toString(), options)
        }
    }


    private fun displayScheduleDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.schedule.collect {
                binding.apply {
                    scheduleTitle.setText(it.title)
                    schedulePlace.setText(it.place)
                    scheduleNote.setText(it.note)
                    isFinishedCb.isChecked = it.isFinished
                    isFulldaySwitch.isActivated = it.isFullDay
                    viewModel.getReminderAsString(resources)?.let {
                        binding.reminderOptions.setText(it)
                    }

                    if (it.startFrom > -1) startFrom.text = Utils.milliToDateAsString(it.startFrom)
                    if (it.finish > -1) finish.text = Utils.milliToDateAsString(it.finish)
                }
            }
        }
    }
}