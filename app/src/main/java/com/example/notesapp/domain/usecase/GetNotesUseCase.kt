package com.example.notesapp.domain.usecase

import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : GetNotesUseCase{
    override suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            repository.getAllData()
        }

}

interface GetNotesUseCase {
    suspend operator fun invoke(): Flow<List<Note>>
}