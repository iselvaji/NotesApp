# NotesApp
Notes and Quotes - Kotlin Multi platform for mobile app

This repository contains a common Kotlin Multiplatform module, a Android project and an iOS project. 
The common module is connected with the Android project via the Gradle multi-project mechanism. 
For use in iOS applications, the shared module compiles into a framework that is exposed to the Xcode project via the internal integration Gradle task. 

Features:

90% of the code is shared, data, business logic, Viewmodel are written in Kotlin
Only UI is written in Android (Compose) and IOS (SwiftUI) native code.

Notes:
- User can create new note by clicking on Floating Action Button
- Notes are saved to local [shared] database and sync with server
- User can delete the notes

Quotes:
- User can see the quotes fetched from https://zenquotes.io/

Application demo

https://github.com/iselvaji/NotesApp/assets/28477412/e222a863-0bda-4c66-984e-f6e52a0bb428


**Architecture**

![image](https://github.com/iselvaji/NotesApp/assets/28477412/6db2ac31-d842-41a9-8b2e-999a9a902939)


**Shared** 

Data layer

Ktor client library is used to communicate with Rest Api client.
Sqldelight library used for database
Repository classes used to communcate with data sources

Business/Domain layer

Usecases for Notes, Notes details and Quotes which communcates to repository layer

Viewmodel

Shared view model for Notes, NotesDetails and Quotes which handle the corresponding events recevived from UI layer
Viewmodel responds the results as a Basic UI state and UI effect.

DI
Koin framework is used for dependency injection

**Android App**

Compose based UI for Notes, Notedetails and Quotes screen
Screens communicate with Shared viewmodels and observe the data changes as a state in compose functions.

**IOS App**

Swift UI based design for Notes, Notedetails and Quotes screen
UI communicate with Shared viewmodels and observe the state changes (ObservedObject) which are published


**Server**

Notes data is saved and retrived in server code written using Ktror server and SQL database.
Server code available in https://github.com/iselvaji/Notesapp-server


