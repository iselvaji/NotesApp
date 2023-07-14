package com.example.notesapp.presentation.notes

import com.example.notesapp.domain.note.Note
import com.example.notesapp.domain.usecase.NotesUseCase
import com.example.notesapp.presentation.base.BaseViewModel
import com.example.notesapp.presentation.base.BasicUiState
import com.example.notesapp.presentation.base.UiEffect
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class NoteListViewModel : BaseViewModel<NoteListScreenEvent, BasicUiState<List<Note>>, UiEffect>() {

    private val useCase: NotesUseCase by inject()

    init {
        handleEvent(NoteListScreenEvent.LoadNotes)
    }

    override fun createInitialState(): BasicUiState<List<Note>> = BasicUiState.Idle

    override fun handleEvent(event: NoteListScreenEvent) {
        println("Event : $event")
        when (event) {
            is NoteListScreenEvent.LoadNotes -> {
                getAllNotes()
            }
            is NoteListScreenEvent.DeleteNotes -> {
                deleteNoteById(event.noteId)
            }
            is NoteListScreenEvent.SyncNotes -> {
                syncNotes()
            }
        }
    }

    private fun getAllNotes() {
        launch {
            useCase.getNotes()
                .onStart {
                    setState { BasicUiState.Loading }
                }.catch {
                    setState {
                        BasicUiState.Error(it.message ?: "Something went wrong!")
                    }
                }.collectLatest {
                    setState {
                        BasicUiState.Success(it)
                    }
                }
        }
    }

    private fun deleteNoteById(id: Long) {
        launch {
            useCase.deleteNoteById(id)
            val notes = useCase.getNotes()
            notes.collectLatest {
                setState { BasicUiState.Success(it)  }
            }
        }
    }

    private fun syncNotes() {
        launch {
            useCase.syncNotes()
                .onStart {
                    setState { BasicUiState.Loading }
                }.catch {
                    setState { BasicUiState.Error(it.message ?: "Something went wrong!") }
                }.collectLatest {
                    setState { BasicUiState.Success(it) }
                }
        }
    }
}