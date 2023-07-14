//
//  FloatingActionButton.swift
//  iosApp
//
//  Created by Selvakumar SM on 13/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct FloatingActionButton<ImageView: View>: ViewModifier {
  let color: Color // background color of the FAB
  let image: ImageView // image shown in the FAB
  let action: () -> Void

  private let size: CGFloat = 45 // size of the FAB circle
  private let margin: CGFloat = 20 // distance from screen edges

  func body(content: Content) -> some View {
    GeometryReader { geo in
      ZStack {
        Color.clear // allows the ZStack to fill the entire screen
        content
        button(geo)
      }
    }
  }

  @ViewBuilder private func button(_ geo: GeometryProxy) -> some View {
    image
      .imageScale(.large)
      .frame(width: size, height: size)
      .background(Circle().fill(color))
      .shadow(color: .gray, radius: 2, x: 1, y: 1)
      .onTapGesture(perform: action)
      .offset(x: (geo.size.width - size) / 2 - margin,
              y: (geo.size.height - size) / 2 - margin)
  }
}
