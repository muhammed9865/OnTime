package com.muhammed.ontime.fragments.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhammed.ontime.Constants
import com.muhammed.ontime.R
import com.muhammed.ontime.activity.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launch {
            delay(1500)
            if (shouldSkipStartUp()) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                requireActivity().startActivity(intent)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_startUpFragment)
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun shouldSkipStartUp(): Boolean {
        return requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getBoolean(Constants.SKIP_STARTUP, false)
    }

}