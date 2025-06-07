package com.example.tangogo.ui.screens.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tangogo.model.User
import com.example.tangogo.model.service.AccountService
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        refreshUser()
    }

    fun refreshUser() {
        viewModelScope.launch {
            accountService.currentUser.collect { current ->
                _user.value = current
            }
        }
    }

    fun deleteAccount(onDeleted: () -> Unit) {
        viewModelScope.launch {
            try {
                accountService.deleteAccount()
                onDeleted()
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error deleting account", e)
            }
        }
    }

    fun updateUserName(newName: String) {
        val parts = newName.trim().split(" ", limit = 2)
        val firstName = parts.getOrNull(0) ?: ""
        val lastName = parts.getOrNull(1) ?: ""

        viewModelScope.launch {
            try {
                accountService.updateUserName(firstName, lastName)
                _user.value = _user.value.copy(firstName = firstName, lastName = lastName)
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error updating name", e)
            }
        }
    }

    fun updatePassword(context: Context, oldPassword: String, newPassword: String) {
        val user = Firebase.auth.currentUser
        val credential = EmailAuthProvider.getCredential(user?.email ?: "", oldPassword)

        viewModelScope.launch {
            try {
                user?.reauthenticate(credential)?.await()
                user?.updatePassword(newPassword)?.await()
                Toast.makeText(context, "Password updated successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Failed to update password", e)
                Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
