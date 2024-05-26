package com.example.notesapp.domain.repository

import com.example.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getAllData(): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}