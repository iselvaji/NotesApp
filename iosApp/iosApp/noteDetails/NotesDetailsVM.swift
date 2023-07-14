//
//  NotesDetailsVM.swift
//  iosApp
//
//  Created by Selvakumar SM on 12/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor
class NotesDetailsVM : NoteDetailViewModel, ObservableObject {
    @Published var state: NoteDetailState = NoteDetailState(noteTitle: "", noteContent:"", inputValidationFailed: false)
    @Published var uiEffect: NoteDetailsUIEffect? = nil
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! NoteDetailState
        })
        
        collect(flow: effect, collect: { state in
            self.uiEffect = state as? NoteDetailsUIEffect
        })
    }
}
