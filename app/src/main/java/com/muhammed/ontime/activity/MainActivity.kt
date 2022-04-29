package com.muhammed.ontime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.muhammed.ontime.R
import com.muhammed.ontime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    private val navController: NavController by lazy {
        Navigation.findNavController(this, binding.mainFragmentsContainer.id)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // Handling the Destinations Radio group changes listener.
        binding.destRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId) {
                binding.scheduleSectionRb.id -> {
                    navController.navigate(R.id.action_notesFragment_to_schedulesFragment)
                }

                binding.noteSectionRb.id -> {
                    navController.navigate(R.id.action_schedulesFragment_to_notesFragment)
                }
            }
        }
    }


}