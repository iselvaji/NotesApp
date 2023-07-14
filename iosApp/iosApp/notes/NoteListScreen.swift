//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Selvakumar SM on 10/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    
    @EnvironmentObject var networkMonitor: NetworkMonitor
    @ObservedObject var viewModel = NotesVM()
    @State private var isShowingDetailView = false
    
    var body: some View {
        NavigationView {
            VStack() {
                NavigationLink(destination: NoteDetailScreen(), isActive:$isShowingDetailView){}
                
                switch self.viewModel.state {
                    case is BasicUiStateLoading:
                        showProgress()

                    case is BasicUiStateError:
                        showErrorMessage()

                    case let success as BasicUiStateSuccess<NSArray>:
                        showNotesList(notes: success.data as! [Note], viewModel: viewModel)
                    
                    default:
                        showProgress()
                }
            }.floatingActionButton(color: Color(hex : "80CBC4")!,
                                  image: Image(systemName: "plus")
               .foregroundColor(.white)) {
                   isShowingDetailView = true
            }.onAppear {
                viewModel.setEvent(event : NoteListScreenEvent.LoadNotes())
            }
        }.onReceive(networkMonitor.$isConnected, perform: { isConnected in
            if isConnected {
                viewModel.setEvent(event : NoteListScreenEvent.SyncNotes())
            }
        })
    }
}

func showNotesList(notes : [Note], viewModel : NotesVM) -> some View {
    List {
        ForEach(notes, id: \.self.id) { note in
            Button(action: {}) {
                NoteItem(note: note, onDeleteClick: {
                    viewModel.setEvent(event: NoteListScreenEvent.DeleteNotes(noteId:note.id?.int64Value ?? 0))
                })
            }
        }
    }.listStyle(.plain)
}


struct showErrorMsg: View {
    var body: some View {
        VStack() {
            Spacer()
            Text("No data available..")
            Spacer()
        }
    }
}

struct showProgress: View {
    var body: some View {
        VStack() {
            Spacer()
            ProgressView()
            Spacer()
        }
    }
}

extension View {
  func floatingActionButton<ImageView: View>(
    color: Color,
    image: ImageView,
    action: @escaping () -> Void) -> some View {
    self.modifier(FloatingActionButton(color: color,image: image,action: action))
  }
}


