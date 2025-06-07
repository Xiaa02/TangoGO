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
        openScreen(Routes.LESSON_HIRAGANAWELCOME)
    }

    fun openKatakana(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_KATAKANAWELCOME)
    }

    fun openHello(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_HELLOWELCOME)
    }

    fun openFamily(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_FAMILYWELCOME)
    }

    fun openFood(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_FOODWELCOME)
    }

    fun openWhere(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_WHEREWELCOME)
    }

    fun openHome(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_HOMEWELCOME)
    }

    fun openDaily(openScreen: (String) -> Unit) {
        openScreen(Routes.LESSON_DAILYWELCOME)
    }

    fun openHiraganaChart(openScreen: (String) -> Unit) {
        openScreen(Routes.HIRAGANA_CHART)
    }
    fun openKatakanaChart(openScreen: (String) -> Unit) {
        openScreen(Routes.KATAKANA_CHART)
    }

    fun openKanjiChart(openScreen: (String) -> Unit) {
        openScreen(Routes.KANJI_CHART)
    }

    fun openSettings(openScreen: (String) -> Unit) {
        openScreen(Routes.SETTINGS)
    }

    fun onLogoutClick(clearAndNavigate: (String) -> Unit) {
        launchCatching {
            accountService.logOut()
            clearAndNavigate(Routes.LOGIN)
        }
    }
}