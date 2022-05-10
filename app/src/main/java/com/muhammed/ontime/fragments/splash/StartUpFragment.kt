package com.muhammed.ontime.fragments.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.muhammed.ontime.Constants
import com.muhammed.ontime.activity.MainActivity
import com.muhammed.ontime.databinding.FragmentStartUpBinding

class StartUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartUpBinding.inflate(inflater, container, false)

        binding.splashSkipStartup.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.splashSkipStartup.isChecked) {
                skipStartUp()
            }
        }

        binding.startUpBtn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            requireActivity().startActivity(intent)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun skipStartUp() {
        requireActivity().getPreferences(Context.MODE_PRIVATE)
            .edit {
                putBoolean(Constants.SKIP_STARTUP, true)
                commit()
            }
    }

}