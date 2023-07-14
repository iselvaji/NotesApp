plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.notesapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.notesapp.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
       // kotlinCompilerExtensionVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    // Koin
    implementation("io.insert-koin:koin-android:3.3.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.3.0")
    implementation( "io.insert-koin:koin-androidx-compose:3.3.0")

    androidTestImplementation("io.insert-koin:koin-test-junit4:3.2.2")
    androidTestImplementation( "io.insert-koin:koin-test-junit5:3.2.2")
    androidTestImplementation("io.insert-koin:koin-test:3.2.2")

    implementation("androidx.navigation:navigation-compose:2.5.3")
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
}