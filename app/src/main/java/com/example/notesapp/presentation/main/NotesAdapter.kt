package com.example.notesapp.presentation.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.data.model.Note
import com.example.notesapp.databinding.FragmentNoteBinding

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

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
            val action = NoteListFragmentDirections.actionNoteListFragmentToDetailNoteFragment(note)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = noteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newNoteList: List<Note>){
        noteList = newNoteList
        notifyDataSetChanged()
    }
}