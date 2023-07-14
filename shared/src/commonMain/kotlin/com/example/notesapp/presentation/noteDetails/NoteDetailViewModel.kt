package com.example.notesapp.presentation.noteDetails

import com.example.notesapp.domain.note.Note
import com.example.notesapp.domain.usecase.AddNoteUseCase
import com.example.notesapp.domain.usecase.NotesDetailsValidateUseCase
import com.example.notesapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.core.component.inject

open class NoteDetailViewModel:  BaseViewModel<NoteDetailScreenEvent, NoteDetailState, NoteDetailsUIEffect>() {

    private val useCase: AddNoteUseCase by inject()
    private val validator: NotesDetailsValidateUseCase by inject()

    init {
        resetState()
    }

    override fun createInitialState() = NoteDetailState()

    override fun handleEvent(event: NoteDetailScreenEvent) {
        when (event) {
            is NoteDetailScreenEvent.SaveNote -> {
               saveNote(uiState.value.noteTitle, uiState.value.noteContent)
            }
            is NoteDetailScreenEvent.NoteTitleChanged -> {
                setState { copy(noteTitle = event.title) }
            }
            is NoteDetailScreenEvent.NoteContentChanged -> {
                setState { copy(noteContent = event.content) }
            }
        }
    }

    private fun saveNote(noteTitle: String, noteContent: String) {
        launch {
            val note = Note(
                id = null,
                title = noteTitle,
                content = noteContent,
                created = Clock.System.now().toEpochMilliseconds()
                // created = DateTimeUtil.now()
            )

            val areNoteDetailsValid = validator.isValid(note)

            if (!areNoteDetailsValid) {
                setState { copy(inputValidationFailed = true) }
                return@launch
            }

            val saveNotesResult = useCase.saveNotes(note)
            if (saveNotesResult.isSuccess) {
               // setState { copy(noteTitle = "", noteContent = "", inputValidationFailed = false, noteSaved = true) }
                resetState()
                setEffect { NoteDetailsUIEffect.NavigateUp }
            }
        }
    }

    private fun resetState() {
        setState { copy(noteTitle = "", noteContent = "", inputValidationFailed = false) }
    }
}