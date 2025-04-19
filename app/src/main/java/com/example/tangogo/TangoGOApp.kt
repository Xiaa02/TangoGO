package com.example.tangogo

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tangogo.common.snackbar.SnackbarManager
import com.example.tangogo.ui.screens.activitylog.ActivitiesScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaScreen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaScreen
import com.example.tangogo.ui.screens.dashboard.DashboardScreen
import com.example.tangogo.ui.screens.lessonHiragana.Hiragana2Screen
import com.example.tangogo.ui.screens.login.LoginScreen
import com.example.tangogo.ui.screens.memory.AHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memory.AHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memory.AHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memory.AHiraganaWriteScreen
import com.example.tangogo.ui.screens.memory.HiraganaChartScreen
import com.example.tangogo.ui.screens.register.RegisterScreen
import com.example.tangogo.ui.screens.welcome.WelcomeScreen
import com.example.tangogo.ui.theme.TangoGOTheme
import com.example.tangogo.utils.Routes
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterial3Api
fun TangoGOApp() {
    val appState = rememberAppState()

    TangoGOTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = appState.snackbarHostState,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = Routes.WELCOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                tangoGOGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
    TangoGOAppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterial3Api
fun NavGraphBuilder.tangoGOGraph(appState: TangoGOAppState) {
    composable(Routes.WELCOME) {
        WelcomeScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.LOGIN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.REGISTER) {
        RegisterScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.DASHBOARD) {
        DashboardScreen(
            openScreen = { route -> appState.navigate(route) },
            clearAndNavigate = { route -> appState.clearAndNavigate(route) }
        )
    }

    composable(Routes.LESSON_HIRAGANA) {
        HiraganaScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANA2) }
        )
    }

    composable(Routes.LESSON_HIRAGANA2) {
        Hiragana2Screen(
            navigateBack = { appState.navController.popBackStack() },
            navigateToDashboard = { appState.navController.navigate(Routes.DASHBOARD) }
        )
    }

    composable(Routes.LESSON_KATAKANA) {
        KatakanaScreen(
            navigateBack = { appState.popUp() },
            onVideoClick = { videoUrl ->
                val context = appState.navController.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                context.startActivity(intent)
            }
        )
    }

    composable(Routes.HIRAGANA_CHART) {
        HiraganaChartScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            onCharClick         = { char ->
                if (char == "あ") {
                    appState.navController.navigate(Routes.A_HIRAGANA_MEMORY)
                }
            }
        )
    }

    composable(Routes.A_HIRAGANA_MEMORY) {
        AHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE)}
        )
    }

    // Mnemonic screen
    composable(Routes.A_HIRAGANA_MNEMONIC) {
        AHiraganaMnemonicScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },  // if you want buttons here, otherwise omit
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    // Stroke‑order screen
    composable(Routes.A_HIRAGANA_STROKE) {
        AHiraganaStrokeScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    // Write‑it screen
    composable(Routes.A_HIRAGANA_WRITE) {
        AHiraganaWriteScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ACTIVITY_LOG) {
        ActivitiesScreen(
            navigateBack = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(Routes.ADD_ACTIVITY) {
//        AddActivityScreen(
//            navigateBack = { appState.popUp() }
//        )
    }

    composable(Routes.WATER_INTAKE) {
//        WaterIntakeScreen(
//            navigateBack = { appState.popUp() }
//        )
    }

    composable(Routes.NUTRI_GO) {
//        NutriGoScreen(
//            navigateBack = { appState.popUp() },
//            openScreen = { route -> appState.navigate(route) }
//        )
    }

    composable(Routes.ADD_MEAL) {
//        AddMealScreen(
//            navigateBack = { appState.popUp() }
//        )
    }

}