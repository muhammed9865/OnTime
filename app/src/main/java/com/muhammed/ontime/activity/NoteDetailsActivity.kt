package com.muhammed.ontime.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.muhammed.ontime.Constants
import com.muhammed.ontime.R
import com.muhammed.ontime.databinding.ActivityNoteDetailsBinding
import com.muhammed.ontime.viewmodel.NoteDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailsActivity : AppCompatActivity() {
    private val binding: ActivityNoteDetailsBinding by lazy {
        ActivityNoteDetailsBinding.inflate(
            LayoutInflater.from(this)
        )
    }
    private val viewModel: NoteDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.buttonsColor)
        setSupportActionBar(binding.noteAdditionToolbar)
        binding.noteAdditionToolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.pinStatus.collect {
                togglePinStatus(it)
            }
        }

        binding.pinIv.setOnClickListener {
            viewModel.togglePinStatus()
        }


        binding.noteAdditionTitle.doOnTextChanged { text, _, _, _ ->
            onTitleChanged(text.toString())
        }

        binding.noteAdditionText.doOnTextChanged { text, _, _, _ ->
            onTextChanged(text.toString())
        }

    }

    override fun onResume() {
        super.onResume()
        if (intent.hasExtra(Constants.NOTE_ID)) {
            val id = intent.getIntExtra(Constants.NOTE_ID, -1)
            viewModel.loadNoteByIdIfEditing(id)
            displayNoteDetails()
        }
    }

    override fun onPause() {
        viewModel.saveNote()
        super.onPause()
    }


    private fun displayNoteDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.note.collect { note ->
                binding.apply {
                    noteAdditionText.setText(note.text)
                    noteAdditionTitle.setText(note.title)
                    togglePinStatus(note.isPinned)
                }
            }
        }

    }

    private fun onTitleChanged(title: String) {
        viewModel.saveTitle(title)
    }

    private fun onTextChanged(text: String) {
        viewModel.saveText(text)
    }

    private fun togglePinStatus(pin: Boolean) {
        if (pin) {
            binding.pinIv.setImageResource(R.drawable.ic_baseline_push_pin_24)
        } else binding.pinIv.setImageResource(R.drawable.ic_outline_push_pin_24)
    }
}