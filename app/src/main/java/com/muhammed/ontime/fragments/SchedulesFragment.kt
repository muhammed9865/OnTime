package com.muhammed.ontime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.muhammed.ontime.Utils.convertToString
import com.muhammed.ontime.databinding.FragmentSchedulesBinding
import java.util.*

class SchedulesFragment : Fragment() {
    private lateinit var binding: FragmentSchedulesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchedulesBinding.inflate(inflater, container, false)

        val cal = Calendar.getInstance()
        binding.datePicker.init(
            cal[Calendar.YEAR],
            cal[Calendar.MONTH],
            cal[Calendar.DAY_OF_MONTH]
        ) { p0, _, _, _ ->
            if (p0 != null) {
                doOnCalendarDateChange(p0)
            }
        }



        return binding.root
    }


    private fun doOnCalendarDateChange(datePicker: DatePicker) {
        val date = datePicker.convertToString()
        Toast.makeText(requireContext(), date, Toast.LENGTH_LONG).show()
    }

}