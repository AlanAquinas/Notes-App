package com.example.notesapp.domain.usecase

import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : GetNotesUseCase{
    override suspend operator fun invoke(note: Note) {
        repository.getAllData()
    }
}

interface GetNotesUseCase {
    suspend operator fun invoke(note: Note)
}