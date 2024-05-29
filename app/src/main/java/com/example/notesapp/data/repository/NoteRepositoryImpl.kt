package com.example.notesapp.data.repository

import com.example.notesapp.data.model.Note
import com.example.notesapp.data.room.NoteDao
import com.example.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao : NoteDao
): NoteRepository {

    override suspend fun getAllData(): Flow<List<Note>> {
        return noteDao.readAllData()
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

}