package com.example.tangogo.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.example.tangogo.model.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val logService: LogService
) : ViewModel() {

    fun onNavigateToProfile(navigate: () -> Unit) {
        logService.log("Navigating to Profile")
        navigate()
    }

    fun onNavigateToHowToUse(navigate: () -> Unit) {
        logService.log("Navigating to How to Use")
        navigate()
    }

    fun onNavigateToAbout(navigate: () -> Unit) {
        logService.log("Navigating to About")
        navigate()
    }

    fun onNavigateToTerms(navigate: () -> Unit) {
        logService.log("Navigating to Terms and Policies")
        navigate()
    }

    fun onBackClick(navigateBack: () -> Unit) {
        logService.log("Back from Settings")
        navigateBack()
    }
}
