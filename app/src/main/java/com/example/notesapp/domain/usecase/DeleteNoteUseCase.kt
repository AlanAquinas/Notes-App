package com.example.notesapp.domain.usecase

import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : DeleteNoteUseCase {
    override suspend fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}

interface DeleteNoteUseCase {
    suspend operator fun invoke(note: Note)
}