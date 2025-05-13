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
        openScreen(Routes.LESSON_HIRAGANA101)
    }

    fun openKatakana(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_KATAKANA101)
    }

    fun openHiraganaChart(openScreen: (String) -> Unit) {
        openScreen(Routes.HIRAGANA_CHART)
    }
    fun openKatakanaChart(openScreen: (String) -> Unit) {
        openScreen(Routes.KATAKANA_CHART)
    }


    fun onLogoutClick(clearAndNavigate: (String) -> Unit) {
        launchCatching {
            accountService.logOut()
            clearAndNavigate(Routes.LOGIN)
        }
    }
}