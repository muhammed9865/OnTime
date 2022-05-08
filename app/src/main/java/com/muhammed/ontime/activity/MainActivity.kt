package com.muhammed.ontime.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.muhammed.ontime.R
import com.muhammed.ontime.databinding.ActivityMainBinding
import com.muhammed.ontime.fragments.NotificationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        setSupportActionBar(binding.mainToolbar)

        // Handling the Destinations Radio group changes listener.
        binding.destRadioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            when (radioGroup.checkedRadioButtonId) {
                binding.scheduleSectionRb.id -> {
                    navController.navigate(R.id.schedulesFragment)
                }

                binding.noteSectionRb.id -> {
                    navController.navigate(R.id.notesFragment)
                }
            }
        }


        binding.addNewNote.setOnClickListener {
            handleCreateNewNote()
        }

        binding.addNewSchedule.setOnClickListener {
            handleCreateNewSchedule()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notifications -> {
                NotificationsFragment().show(supportFragmentManager, null)
                true
            }

            else -> false
        }
    }

    private fun handleCreateNewNote() {
        val intent = Intent(this, NoteDetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)

    }

    private fun handleCreateNewSchedule() {
        val intent = Intent(this, ScheduleDetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }


}