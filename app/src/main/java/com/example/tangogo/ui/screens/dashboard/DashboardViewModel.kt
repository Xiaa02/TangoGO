package com.example.tangogo.ui.screens.dashboard

import com.example.tangogo.model.service.AccountService
import com.example.tangogo.model.service.LogService
import com.example.tangogo.ui.screens.TangoGOViewModel
import com.example.tangogo.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : TangoGOViewModel(logService) {
    val currentUser = accountService.currentUser

    fun openHiragana(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_HIRAGANA)
    }

    fun openKatakana(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_KATAKANA)
    }

    fun openDailyWaterIntake(openScreen: (String) -> Unit) {
        openScreen(Routes.WATER_INTAKE)
    }

    fun openStepCounter(openScreen: (String) -> Unit) {
        openScreen(Routes.STEP_COUNTER)
    }

    fun openNutriGo(openScreen: (String) -> Unit) {
        openScreen(Routes.NUTRI_GO)
    }

    fun openActivityLog(openScreen: (String) -> Unit) {
        openScreen(Routes.ACTIVITY_LOG)
    }

    fun onLogoutClick(clearAndNavigate: (String) -> Unit) {
        launchCatching {
            accountService.logOut()
            clearAndNavigate(Routes.LOGIN)
        }
    }
}