//
//  QuotesScreen.swift
//  iosApp
//
//  Created by Selvakumar SM on 06/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct QuotesScreen : View {
    @ObservedObject var viewModel = QuotesVM()
    
    var body: some View {
        NavigationView {
            switch self.viewModel.state {
                case is BasicUiStateLoading:
                    showProgressView()

                case is BasicUiStateError:
                    showErrorMessage()

                case let success as BasicUiStateSuccess<NSArray>:
                    showQuotesList(data: success.data as! [Quote])
                
                default:
                    showProgressView()
            }
        }.onAppear {
            viewModel.setEvent(event : QuoteListScreenEvent.LoadQuotes())
        }
    }
}

struct showQuotesList: View {
    private let quotes: [Quote]
        
    init(data: [Quote]) {
        self.quotes = data
    }
    
    var body: some View {
        List {
            ForEach(self.quotes, id: \.self.author) { quote in
                showQuoteItem(quote : quote)
            }
        }.listStyle(.plain)
    }

}

struct showQuoteItem: View {
    var quote: Quote
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(quote.quote)
            Text("-" + quote.author)
        }
    }
}

struct showErrorMessage: View {
   // var msg: String
    var body: some View {
        VStack() {
            Spacer()
            Text("No data available ")
            Spacer()
        }
    }
}

struct showProgressView: View {
    var body: some View {
        VStack() {
            Spacer()
            ProgressView()
            Spacer()
        }
    }
}
