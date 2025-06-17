package com.example.tangogo.ui.screens.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tangogo.model.User
import com.example.tangogo.model.service.AccountService
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
            accountService.currentUser.collect { currentUser ->
                // Ensure currentUser is not null or empty
                if (true) {
                    _user.value = currentUser  // Update user state with the current user data
                } else {
                    Log.e("ProfileViewModel", "User data is null")
                }
            }
        }
    }

    fun deleteAccount(context: Context, onDeleted: () -> Unit) {
        val user = Firebase.auth.currentUser
        Log.d("ProfileViewModel", "Attempting to delete account...")

        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("ProfileViewModel", "Account deleted successfully")
                Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()
                onDeleted()  // Perform post-deletion actions (e.g., navigate back)
            } else {
                Log.e("ProfileViewModel", "Error deleting account", task.exception)
                Toast.makeText(context, "Failed to delete account. Please log out then re-login and try again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateUserName(context: Context, newName: String) {
        val parts = newName.trim().split(" ", limit = 2)
        val firstName = parts.getOrNull(0) ?: ""
        val lastName = parts.getOrNull(1) ?: ""

        viewModelScope.launch {
            try {
                val user = Firebase.auth.currentUser
                user?.updateProfile(userProfileChangeRequest {
                    displayName = "$firstName $lastName"
                })?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Update the state after updating Firebase
                        _user.value = _user.value.copy(firstName = firstName, lastName = lastName)

                        // Now update the name in Firestore as well
                        val userId = Firebase.auth.currentUser?.uid ?: return@addOnCompleteListener
                        updateUserNameInFirestore(userId, firstName, lastName)

                        // Show a success toast
                        Toast.makeText(context, "Name updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("ProfileViewModel", "Error updating name", task.exception)
                        Toast.makeText(context, "Failed to update name in Authentication", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error updating name", e)

                // Show a failure toast if there's an error
                Toast.makeText(context, "Failed to update name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUserNameInFirestore(userId: String, firstName: String, lastName: String) {
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(userId)

        val updatedData = mapOf(
            "firstName" to firstName,
            "lastName" to lastName
        )

        userRef.update(updatedData)
            .addOnSuccessListener {
                Log.d("ProfileViewModel", "User name updated in Firestore successfully")
            }
            .addOnFailureListener { e ->
                Log.e("ProfileViewModel", "Error updating user name in Firestore", e)
            }
    }

    fun updatePassword(context: Context, oldPassword: String, newPassword: String) {
        val user = Firebase.auth.currentUser ?: return // Early exit if no user is found
        val email = user.email ?: return // Early exit if email is null

        val credential = EmailAuthProvider.getCredential(email, oldPassword)

        user.reauthenticate(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                    if (updateTask.isSuccessful) {
                        Toast.makeText(context, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to update password", Toast.LENGTH_SHORT).show()
                        Log.e("ProfileViewModel", "Update error", updateTask.exception)
                    }
                }
            } else {
                Toast.makeText(context, "Old password is incorrect", Toast.LENGTH_SHORT).show()
                Log.e("ProfileViewModel", "Reauth error", task.exception)
            }
        }
    }
}


