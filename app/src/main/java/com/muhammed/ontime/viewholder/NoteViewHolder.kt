package com.muhammed.ontime.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.ontime.Utils
import com.muhammed.ontime.databinding.ListItemNoteBinding
import com.muhammed.ontime.pojo.Note

class NoteViewHolder(private val binding: ListItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note, setOnNoteClickListener: ((note_id: Int) -> Unit)?) {
        binding.noteDate.text = Utils.milliToDateAsString(note.initDate)
        binding.noteText.text = note.text
        binding.noteTitle.text = note.title
        binding.notePinIcon.visibility = if (note.isPinned) View.VISIBLE else View.GONE
        binding.noteCard.setOnClickListener {
            if (setOnNoteClickListener != null) {
                setOnNoteClickListener(note.id!!)
            }
        }
    }
}