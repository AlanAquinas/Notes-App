package com.example.notesapp.presentation.main

import NoteDiffCallback
import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.data.model.Note
import com.example.notesapp.databinding.FragmentNoteBinding

class NotesAdapter(private val onItemClicked: (Note) -> Unit ): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var noteList: List<Note> = emptyList()

    class NotesViewHolder(val binding: FragmentNoteBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = FragmentNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = noteList[position]
        val myHolder = holder.binding

        myHolder.noteNameTv.text = note.name
        myHolder.dateTv.text = note.date

        holder.itemView.setOnClickListener {
//            listener.onNoteClick(note)
            onItemClicked(note)
        }

    }

    override fun getItemCount(): Int = noteList.size

    fun setData(newNoteList: List<Note>){
        val diffCallback = NoteDiffCallback(noteList, newNoteList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        noteList = newNoteList
        diffResult.dispatchUpdatesTo(this)
    }
}