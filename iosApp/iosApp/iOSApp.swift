import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @StateObject var networkMonitor = NetworkMonitor()
    
    init() {
        KoinKt.doInitKoin()
        UITabBar.appearance().backgroundColor = UIColor(rgb: 0x80CBC4)
    }

    var body: some Scene {
        WindowGroup {
            NavigationView {
                VStack {
                    tabsView
                }.environmentObject(networkMonitor)
            }
        }
    }
    
    var tabsView: some View {
        TabView {
            NoteListScreen()
            .tabItem({
                Image(systemName: "pencil")
                Text("Notes")
            }).tag(1)
                
            QuotesScreen()
                .tabItem({
                Image(systemName: "doc.plaintext")
                Text("Quotes")
            }).tag(2)
        }

    }
}
