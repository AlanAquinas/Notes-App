package com.example.notesapp.di

import com.example.notesapp.domain.repository.NoteRepository
import com.example.notesapp.domain.usecase.AddNoteUseCase
import com.example.notesapp.domain.usecase.AddNoteUseCaseImpl
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.DeleteNoteUseCaseImpl
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCaseImpl
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideAddNoteUseCase(
        repository: NoteRepository
    ) : AddNoteUseCase {
        return AddNoteUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateNoteUseCase(
        repository: NoteRepository
    ) : UpdateNoteUseCase {
        return UpdateNoteUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(
        repository: NoteRepository
    ) : GetNotesUseCase {
        return GetNotesUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteNotesUseCase(
        repository: NoteRepository
    ) : DeleteNoteUseCase {
        return DeleteNoteUseCaseImpl(repository)
    }

}