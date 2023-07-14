//
//  NotesVM.swift
//  iosApp
//
//  Created by Selvakumar SM on 10/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Combine

@MainActor
class NotesVM : NoteListViewModel, ObservableObject {
    @Published var state: BasicUiState = BasicUiStateIdle.shared
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! BasicUiState
        })
    }
}
