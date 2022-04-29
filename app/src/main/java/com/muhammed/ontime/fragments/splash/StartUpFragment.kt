package com.muhammed.ontime.fragments.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.muhammed.ontime.R
import com.muhammed.ontime.activity.MainActivity
import com.muhammed.ontime.databinding.FragmentStartUpBinding

class StartUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartUpBinding.inflate(inflater, container, false)

        binding.startUpBtn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            requireActivity().startActivity(intent)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}