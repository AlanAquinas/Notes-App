package com.example.notesapp.domain.usecase

import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : UpdateNoteUseCase {
    override suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}

interface UpdateNoteUseCase {
    suspend operator fun invoke(note: Note)
}