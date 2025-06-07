package com.example.tangogo.model.service

import android.net.Uri

interface StorageService {
    suspend fun uploadAvatar(uri: Uri): String
}