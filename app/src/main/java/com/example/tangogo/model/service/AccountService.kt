package com.example.tangogo.model.service

import com.example.tangogo.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun register(firstName: String, lastName: String, email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun deleteAccount()
    suspend fun logOut()

    suspend fun updateUserName(firstName: String, lastName: String)
    suspend fun updateAvatarUrl(url: String)

}
