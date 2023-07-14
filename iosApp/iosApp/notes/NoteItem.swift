//
//  NoteItem.swift
//  iosApp
//
//  Created by Selvakumar SM on 10/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    var note: Note
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "xmark").foregroundColor(.black)
                }
            }.padding(.bottom, 3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
           HStack {
                Spacer()
                Text(DateTimeUtil().formatNoteDate(dateTimeMs: note.created))
                    .font(.footnote)
                    .fontWeight(.light)
            }
        }
        .padding()
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}
