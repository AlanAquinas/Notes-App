package com.example.notesapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.R
import com.example.notesapp.data.model.Note
import com.example.notesapp.domain.usecase.AddNoteUseCase
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import com.example.notesapp.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.catch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel(){
    private val _noteList: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()
    val errorMessage = MutableLiveData<UiText?>()

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            getNotesUseCase()
                .catch {error ->
                    errorMessage.postValue(UiText.DynamicString(error.message))
                }
                .collect { notes ->
                    if (notes.isEmpty()) {
                        errorMessage.setValue(
                            UiText.StringResource(
                                R.string.you_dont_have_any_notes_yet
                            )
                        )
                    } else {
                        errorMessage.postValue(null)
                    }
                    _noteList.value = notes
                }
        }
    }

    fun addNote(note: Note){
        viewModelScope.launch {
            addNoteUseCase(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            Log.d("NoteViewModel", "Updated note: $note")
            updateNoteUseCase(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}