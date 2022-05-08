package com.muhammed.ontime.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammed.ontime.R
import com.muhammed.ontime.adapter.SchedulesAdapter
import com.muhammed.ontime.databinding.FragmentNotificationsBinding
import com.muhammed.ontime.viewmodel.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : DialogFragment() {
    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel: NotificationsViewModel by viewModels()
    private val mAdapter: SchedulesAdapter by lazy { SchedulesAdapter(isForNotification = true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_OnTime)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.notificationsToolbar.setNavigationOnClickListener {
            try {
                dismiss()

            } catch (e: IllegalStateException) {
                Log.e(TAG, "onCreateView: ")
            }

        }
        binding.notificationsSchedulesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        viewModel.fetchSchedules()

        lifecycleScope.launch {
            viewModel.schedulesList.collect {
                mAdapter.submitList(it)
                binding.emptyList.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        return binding.root
    }

    companion object {
        private const val TAG = "NotificationsFragment"
    }
}