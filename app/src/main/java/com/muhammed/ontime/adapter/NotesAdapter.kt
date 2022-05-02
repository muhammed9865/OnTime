package com.muhammed.ontime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.muhammed.ontime.databinding.ListItemNoteBinding
import com.muhammed.ontime.pojo.Note
import com.muhammed.ontime.viewholder.NoteViewHolder

class NotesAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffUtil()) {
    private var onNoteClickListenr: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ListItemNoteBinding.inflate(LayoutInflater.from(parent.context))
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onNoteClickListenr)
    }


    class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }

    fun setOnNoteClickListener(onNoteClickListener: (Int) -> Unit) {
        this.onNoteClickListenr = onNoteClickListener
    }
}