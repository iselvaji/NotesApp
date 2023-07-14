//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Selvakumar SM on 21/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {

    @Environment(\.presentationMode) var presentationMode
    @ObservedObject var viewModel = NotesDetailsVM()
  
    @State private var title: String = ""
    @State private var content: String = ""
    
    init() {
        UINavigationBar.appearance().backgroundColor = UIColor(rgb: 0x80CBC4)
    }
    
    var body: some View {
        VStack() {
            Spacer().frame(height: 30)
            
            TextField("Title", text:$title)
                .textFieldStyle(.roundedBorder).padding(10.0)
                .onChange(of: title) { newValue in
                    viewModel.handleEvent(event: NoteDetailScreenEvent.NoteTitleChanged(title: title))
                }
            
            TextField("Notes", text:$content).textFieldStyle(.roundedBorder).padding(10.0)
                .onChange(of: content) { newValue in
                    viewModel.handleEvent(event: NoteDetailScreenEvent.NoteContentChanged(content: content))
                }
            
            if (self.viewModel.state.inputValidationFailed) {
                Text("Please provide required details")
            }
            
            Button(action: {
                viewModel.handleEvent(event: NoteDetailScreenEvent.SaveNote())
            }) {
                Text("Save")
            }.padding(10.0)
            .background(Color(hex : "80CBC4")!)
            .foregroundColor(Color.black)
            .cornerRadius(5.0)
        
            Spacer()
            
        }.onChange(of: self.viewModel.uiEffect) { newEffect in
            switch newEffect {
                case NoteDetailsUIEffect.NavigateUp():
                    self.presentationMode.wrappedValue.dismiss()
                default: break
            }
        }.navigationBarTitle(Text("Add Note"))
    }
    
}
