package com.muhammed.ontime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammed.ontime.Utils.convertToString
import com.muhammed.ontime.adapter.DaysWithSchedulesAdapter
import com.muhammed.ontime.databinding.FragmentSchedulesBinding
import com.muhammed.ontime.viewmodel.SchedulesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SchedulesFragment : Fragment() {
    private lateinit var binding: FragmentSchedulesBinding
    private val mAdapter: DaysWithSchedulesAdapter by lazy { DaysWithSchedulesAdapter() }
    private val viewModel: SchedulesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchedulesBinding.inflate(inflater, container, false)
        setupSchedulesList()
        viewModel.loadDaysWithSchedules()

        lifecycleScope.launch {
            viewModel.daysWithSchedules.collect {
                mAdapter.submitList(it)
            }
        }

        val cal = Calendar.getInstance()




        return binding.root
    }

    private fun setupSchedulesList() {
        val layoutManager = object : LinearLayoutManager(requireContext()) {

        }

        binding.schedulesRv.apply {
            adapter = mAdapter
            setLayoutManager(layoutManager)
        }
    }

    private fun doOnCalendarDateChange(datePicker: DatePicker) {
        val date = datePicker.convertToString()
        Toast.makeText(requireContext(), date, Toast.LENGTH_LONG).show()
    }

}