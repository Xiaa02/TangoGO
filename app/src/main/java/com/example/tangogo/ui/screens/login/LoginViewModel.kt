package com.example.tangogo.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import com.example.tangogo.common.ext.isValidEmail
import com.example.tangogo.common.snackbar.SnackbarManager
import com.example.tangogo.model.service.AccountService
import com.example.tangogo.model.service.LogService
import com.example.tangogo.ui.screens.TangoGOViewModel
import com.example.tangogo.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.tangogo.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService
) : TangoGOViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }
        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(Routes.DASHBOARD, Routes.LOGIN)
        }
    }

    fun onNotRegistered(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Routes.REGISTER, Routes.LOGIN)
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = ""
)
