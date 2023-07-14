//
//  QuotesVM.swift
//  iosApp
//
//  Created by Selvakumar SM on 06/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor
class QuotesVM : QuotesViewModel, ObservableObject {
    @Published var state: BasicUiState = BasicUiStateIdle.shared
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! BasicUiState
        })
    }
}
