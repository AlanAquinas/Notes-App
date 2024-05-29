package com.example.notesapp.domain.usecase

import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {
    override suspend operator fun invoke(note: Note) =
        withContext(Dispatchers.IO) {
            repository.addNote(note)
        }
}

interface AddNoteUseCase {
    suspend operator fun invoke(note: Note)
}