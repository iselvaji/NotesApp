package com.example.notesapp.android.util

import kotlinx.coroutines.flow.Flow

/**
 * Utility for reporting app connectivity status
 */
interface ConnectivityObserver {
    val isOnline: Flow<Boolean>
}