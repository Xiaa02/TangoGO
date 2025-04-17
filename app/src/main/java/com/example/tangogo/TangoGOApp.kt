package com.example.tangogo

import KatakanaScreen
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
import com.example.tangogo.ui.screens.activitylog.AddActivityScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaScreen
import com.example.tangogo.ui.screens.dashboard.DashboardScreen
import com.example.tangogo.ui.screens.login.LoginScreen
import com.example.tangogo.ui.screens.nutrigo.AddMealScreen
import com.example.tangogo.ui.screens.nutrigo.NutriGoScreen
import com.example.tangogo.ui.screens.register.RegisterScreen
import com.example.tangogo.ui.screens.stepcounter.StepCounterScreen
import com.example.tangogo.ui.screens.waterintake.WaterIntakeScreen
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
            navigateBack = { appState.popUp() }
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

    composable(Routes.STEP_COUNTER) {
        StepCounterScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.ACTIVITY_LOG) {
        ActivitiesScreen(
            navigateBack = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(Routes.ADD_ACTIVITY) {
        AddActivityScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.WATER_INTAKE) {
        WaterIntakeScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.NUTRI_GO) {
        NutriGoScreen(
            navigateBack = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(Routes.ADD_MEAL) {
        AddMealScreen(
            navigateBack = { appState.popUp() }
        )
    }

}