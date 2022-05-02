package com.muhammed.ontime.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammed.ontime.Constants
import com.muhammed.ontime.activity.NoteDetailsActivity
import com.muhammed.ontime.adapter.NotesAdapter
import com.muhammed.ontime.databinding.FragmentNotesBinding
import com.muhammed.ontime.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }
    private val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        binding.notesRv.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        loadNotes()
        // On Note click listener.
        notesAdapter.setOnNoteClickListener { id ->
            val intent = Intent(requireContext(), NoteDetailsActivity::class.java)
            intent.putExtra(Constants.NOTE_ID, id)
            startNoteDetailsActivity.launch(intent)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.notesList.collect {
                notesAdapter.submitList(it)
            }
        }

        binding.notesSearchView.doOnTextChanged { text, _, _, _ ->
            text?.let { viewModel.searchNotesByTitle(it.toString()) }
        }


        return binding.root
    }

    private fun loadNotes() = viewModel.loadNotes()

    private val startNoteDetailsActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            lifecycleScope.launch {
                loadNotes()
            }
        }
}