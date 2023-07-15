# NotesApp
Notes and Quotes - Kotlin Multi platform for mobile app

This repository contains a common Kotlin Multiplatform module, a Android project and an iOS project. 
The common module is connected with the Android project via the Gradle multi-project mechanism. For use in iOS applications, the shared module compiles into a framework that is exposed to the Xcode project via the internal integration Gradle task. 
This framework connects to the Xcode project that builds an iOS application.

Features:

90% of the code is shared: data, business logic, Viewmodel are written in Kotlin
Only UI is written in Android and IOS native code.

Notes:
- User can create new note by clicking on Floating Action Button
- Notes are saved to local [shared] database and sync with server
- User can delete the notes

Quotes:
- User can see the quotes fetched from https://zenquotes.io/

Architecture

![image](https://github.com/iselvaji/NotesApp/assets/28477412/6db2ac31-d842-41a9-8b2e-999a9a902939)

