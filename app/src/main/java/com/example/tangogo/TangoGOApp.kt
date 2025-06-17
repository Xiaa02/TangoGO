@file:Suppress("KotlinConstantConditions")

package com.example.tangogo

import android.content.res.Resources
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tangogo.common.snackbar.SnackbarManager
import com.example.tangogo.ui.screens.dashboard.DashboardScreen
import com.example.tangogo.ui.screens.login.LoginScreen
import com.example.tangogo.ui.screens.register.RegisterScreen
import com.example.tangogo.ui.screens.welcome.WelcomeScreen
import com.example.tangogo.ui.screens.settings.SettingsScreen
import com.example.tangogo.ui.screens.lessonComplete.LessonCompleteScreen
import com.example.tangogo.ui.screens.lessonDailyLife.DailyL1Screen
import com.example.tangogo.ui.screens.lessonDailyLife.DailyL2Screen
import com.example.tangogo.ui.screens.lessonDailyLife.DailyL3Screen
import com.example.tangogo.ui.screens.lessonDailyLife.DailyWelcomeScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaWelcomeScreen
//import com.example.tangogo.ui.screens.lessonHiragana.Hiragana101Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaL1Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaL2Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaSpeakScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaTableScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaQ1Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaQ2Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaWelcomeScreen
//import com.example.tangogo.ui.screens.lessonKatakana.Katakana101Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaL1Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaL2Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaQ1Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaQ2Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaSpeakScreen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaTableScreen
import com.example.tangogo.ui.screens.lessonHello.HelloWelcomeScreen
import com.example.tangogo.ui.screens.lessonHello.HelloL1Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL2Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL3Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL4Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL5Screen
import com.example.tangogo.ui.screens.lessonHello.HelloQ1Screen
import com.example.tangogo.ui.screens.lessonHello.HelloQ2Screen
import com.example.tangogo.ui.screens.lessonFamily.FamilyWelcomeScreen
import com.example.tangogo.ui.screens.lessonFamily.FamilyL1Screen
import com.example.tangogo.ui.screens.lessonFamily.FamilyL2Screen
import com.example.tangogo.ui.screens.lessonFamily.FamilyL3Screen
import com.example.tangogo.ui.screens.lessonFamily.FamilyL4Screen
import com.example.tangogo.ui.screens.lessonFood.FoodWelcomeScreen
import com.example.tangogo.ui.screens.lessonFood.FoodL1Screen
import com.example.tangogo.ui.screens.lessonFood.FoodL2Screen
import com.example.tangogo.ui.screens.lessonFood.FoodL3Screen
import com.example.tangogo.ui.screens.lessonHome.HomeL1Screen
import com.example.tangogo.ui.screens.lessonHome.HomeL2Screen
import com.example.tangogo.ui.screens.lessonHome.HomeL3Screen
import com.example.tangogo.ui.screens.lessonHome.HomeWelcomeScreen
import com.example.tangogo.ui.screens.lessonWhere.WhereL1Screen
import com.example.tangogo.ui.screens.lessonWhere.WhereL2Screen
import com.example.tangogo.ui.screens.lessonWhere.WhereL3Screen
import com.example.tangogo.ui.screens.lessonWhere.WhereWelcomeScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiraganaChartScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.FuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.FuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.FuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.FuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.HaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.HaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.HaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.HeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.HeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.HeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.HoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.HoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.HoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.KeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.KeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.KiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.KiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.KoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.KoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.KuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.KuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.KuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.MaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.MaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.MaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.MaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.MeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.MeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.MeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.MeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.MiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.MiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.MiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.MiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.MoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.MoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.MoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.MoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.MuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.MuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.MuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.MuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.NuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.NuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.NuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.NuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.RaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.RaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.RaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.RaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ReHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.ReHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.ReHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.ReHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.RiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.RiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.RiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.RiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.RoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.RoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.RoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.RoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.RuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.RuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.RuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.RuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.SaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.SaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.SaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.SaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.SeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.SeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.SeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.SeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ShiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.ShiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.ShiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.ShiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.SoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.SoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.SoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.SoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.SuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.SuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.SuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.SuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.TaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.TaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.TaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.TaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.TeHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.TeHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.TeHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.TeHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ToHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.ToHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.ToHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.ToHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.TsuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.TsuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.TsuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.TsuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.WaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.WaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.WaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.WaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.WoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.WoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.WoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.WoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.YaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.YaHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.YaHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.YaHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.YoHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.YoHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.YoHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.YoHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.YuHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.YuHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.YuHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.YuHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.AKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.AKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.AKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.AKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.ChiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.ChiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.ChiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.ChiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.EKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.EKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.EKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.EKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.FuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.FuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.FuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.FuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.HaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.HaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.HaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.HaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.HeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.HeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.HeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.HeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.HiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.HiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.HiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.HiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.HoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.HoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.HoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.HoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.IKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.IKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.IKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.IKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.KaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.KaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.KaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.KaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.KatakanaChartScreen
import com.example.tangogo.ui.screens.memoryKatakana.KeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.KeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.KeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.KeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.KiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.KiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.KiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.KiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.KoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.KoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.KoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.KoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.KuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.KuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.KuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.KuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.MaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.MaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.MaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.MaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.MeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.MeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.MeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.MeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.MiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.MiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.MiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.MiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.MoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.MoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.MoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.MoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.MuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.MuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.MuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.MuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.NuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.NuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.NuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.NuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.OKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.OKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.OKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.OKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.RaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.RaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.RaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.RaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.ReKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.ReKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.ReKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.ReKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.RiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.RiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.RiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.RiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.RoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.RoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.RoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.RoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.RuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.RuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.RuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.RuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.SaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.SaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.SaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.SaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.SeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.SeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.SeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.SeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.ShiKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.ShiKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.ShiKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.ShiKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.SoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.SoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.SoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.SoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.SuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.SuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.SuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.SuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.TaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.TaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.TaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.TaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.TeKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.TeKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.TeKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.TeKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.ToKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.ToKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.ToKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.ToKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.TsuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.TsuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.TsuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.TsuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.UKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.UKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.UKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.UKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.WaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.WaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.WaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.WaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.WoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.WoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.WoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.WoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.YaKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.YaKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.YaKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.YaKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.YoKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.YoKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.YoKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.YoKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKatakana.YuKatakanaMemoryScreen
import com.example.tangogo.ui.screens.memoryKatakana.YuKatakanaMnemonicScreen
import com.example.tangogo.ui.screens.memoryKatakana.YuKatakanaStrokeScreen
import com.example.tangogo.ui.screens.memoryKatakana.YuKatakanaWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.KanjiChartScreen
import com.example.tangogo.ui.screens.memoryKanji.SakanaKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.SakanaKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.SakanaKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.SakanaKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.NikuKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.NikuKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.NikuKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.NikuKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.MizuKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.MizuKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.MizuKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.MizuKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.TabemasuKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.TabemasuKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.TabemasuKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.TabemasuKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.OokiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.OokiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.OokiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.OokiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.ChiisaiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.ChiisaiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.ChiisaiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.ChiisaiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijihanKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijihanKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijihanKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.IchijihanKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.GetsuyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.GetsuyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.GetsuyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.GetsuyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.KayoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.KayoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.KayoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.KayoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.SuiyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.SuiyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.SuiyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.SuiyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.MokuyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.MokuyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.MokuyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.MokuyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.KinyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.KinyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.KinyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.KinyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.DoyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.DoyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.DoyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.DoyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.memoryKanji.NichiyoubiKanjiMemoryScreen
import com.example.tangogo.ui.screens.memoryKanji.NichiyoubiKanjiMnemonicScreen
import com.example.tangogo.ui.screens.memoryKanji.NichiyoubiKanjiStrokeScreen
import com.example.tangogo.ui.screens.memoryKanji.NichiyoubiKanjiWriteScreen
import com.example.tangogo.ui.screens.profile.ProfileViewModel
import com.example.tangogo.ui.screens.settings.AboutDevScreen
import com.example.tangogo.ui.screens.settings.EditNameScreen
import com.example.tangogo.ui.screens.settings.EditPasswordScreen
import com.example.tangogo.ui.screens.settings.ProfileScreen
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

@Suppress("DUPLICATE_BRANCH_CONDITION_IN_WHEN")
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
    
    composable(Routes.SETTINGS) {
        SettingsScreen(
            navigateBack = { appState.navigate(Routes.DASHBOARD) },
            navigateToProfile = { appState.navigate(Routes.PROFILE) },
            navigateToAbout = { appState.navigate(Routes.ABOUT) }
        )
    }

    composable(Routes.ABOUT) {
        AboutDevScreen(
            navigateBack = { appState.navController.popBackStack() }
        )
    }

    composable(Routes.PROFILE) {
        val viewModel: ProfileViewModel = hiltViewModel()
        val userState by viewModel.user.collectAsState()

        ProfileScreen(
            viewModel = viewModel,
            user = userState,
            navigateToSettings = { appState.navController.navigate(Routes.SETTINGS) },
            onDeleteAccount = { context ->
                viewModel.deleteAccount(context) {
                    appState.navController.navigate(Routes.WELCOME)
                }
            },
            navigateToEditName = { appState.navController.navigate(Routes.EDITNAME) },
            navigateToEditPassword = { appState.navController.navigate(Routes.EDITPASSWORD) },
            navigateToWelcome = { appState.navController.navigate(Routes.WELCOME) }
        )
    }

    composable(Routes.EDITNAME) {
        val viewModel: ProfileViewModel = hiltViewModel()
        val userState by viewModel.user.collectAsState()

        val fullName = "${userState.firstName} ${userState.lastName}".trim()
        val context = LocalContext.current

        EditNameScreen(
            currentName = fullName,
            onSave = { newName ->
                viewModel.updateUserName(context, newName)
            },
            navigateBack = { appState.navController.navigate(Routes.PROFILE) },
            navigateToSettings = { appState.navController.navigate(Routes.SETTINGS) }
        )
    }


    composable(Routes.EDITPASSWORD) {
        val viewModel: ProfileViewModel = hiltViewModel()

        EditPasswordScreen(
            viewModel = viewModel,
            navigateBack = { appState.navController.navigate(Routes.PROFILE) },
            navigateToSettings = { appState.navController.navigate(Routes.SETTINGS) }
        )
    }

    composable(Routes.LESSON_COMPLETE) {
        LessonCompleteScreen(
            navigateToDashboard = { appState.navController.navigate(Routes.DASHBOARD) }
        )
    }

    composable(Routes.LESSON_HIRAGANAWELCOME) {
        HiraganaWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.HIRAGANA_TABLE) }
        )
    }
//    composable(Routes.LESSON_HIRAGANA101) {
//        Hiragana101Screen(
//            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
//            //navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
//            navigateToNext = { appState.navController.navigate(Routes.HIRAGANA_TABLE) }
//        )
//    }
    composable(Routes.HIRAGANA_TABLE) {
        HiraganaTableScreen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HIRAGANAWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAL1) }
        )
    }
    composable(Routes.LESSON_HIRAGANAL1) {
        HiraganaL1Screen(
            navigateBack = { appState.navController.navigate(Routes.HIRAGANA_TABLE) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAL2) }
        )
    }
    composable(Routes.LESSON_HIRAGANAL2) {
        HiraganaL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HIRAGANAL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ1) }
        )
    }
    composable(Routes.LESSON_HIRAGANAQ1) {
        HiraganaQ1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HIRAGANAL2) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ2) }
        )
    }
    composable(Routes.LESSON_HIRAGANAQ2) {
        HiraganaQ2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANASPEAK) }
        )
    }
    composable(Routes.LESSON_HIRAGANASPEAK) {
        HiraganaSpeakScreen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_KATAKANAWELCOME) {
        KatakanaWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.KATAKANA_TABLE) }
        )
    }
//    composable(Routes.LESSON_KATAKANA101) {
//        Katakana101Screen(
//            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
//            navigateToNext = { appState.navController.navigate(Routes.KATAKANA_TABLE) }
//        )
//    }
    composable(Routes.KATAKANA_TABLE) {
        KatakanaTableScreen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_KATAKANAWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAL1) }
        )
    }
    composable(Routes.LESSON_KATAKANAL1) {
        KatakanaL1Screen(
            navigateBack = { appState.navController.navigate(Routes.KATAKANA_TABLE) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAL2) }
        )
    }
    composable(Routes.LESSON_KATAKANAL2) {
        KatakanaL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_KATAKANAL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAQ1) }
        )
    }
    composable(Routes.LESSON_KATAKANAQ1) {
        KatakanaQ1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_KATAKANAL2) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAQ2) }
        )
    }
    composable(Routes.LESSON_KATAKANAQ2) {
        KatakanaQ2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_KATAKANAQ1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANASPEAK) }
        )
    }
    composable(Routes.LESSON_KATAKANASPEAK) {
        KatakanaSpeakScreen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_KATAKANAQ2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_HELLOWELCOME) {
        HelloWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL1) }
        )
    }
    composable(Routes.LESSON_HELLOL1) {
        HelloL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL2) }
        )
    }
    composable(Routes.LESSON_HELLOL2) {
        HelloL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL3) }
        )
    }
    composable(Routes.LESSON_HELLOL3) {
        HelloL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOL2) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL4) }
        )
    }
    composable(Routes.LESSON_HELLOL4) {
        HelloL4Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOL3) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL5) }
        )
    }
    composable(Routes.LESSON_HELLOL5) {
        HelloL5Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOL4) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOQ1) }
        )
    }
    composable(Routes.LESSON_HELLOQ1) {
        HelloQ1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOL5) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOQ2) }
        )
    }
    composable(Routes.LESSON_HELLOQ2) {
        HelloQ2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HELLOQ1) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_FAMILYWELCOME) {
        FamilyWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FAMILYL1) }
        )
    }
    composable(Routes.LESSON_FAMILYL1) {
        FamilyL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FAMILYWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FAMILYL2) }
        )
    }
    composable(Routes.LESSON_FAMILYL2) {
        FamilyL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FAMILYL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FAMILYL3) }
        )
    }
    composable(Routes.LESSON_FAMILYL3) {
        FamilyL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FAMILYL2) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FAMILYL4) }
        )
    }
    composable(Routes.LESSON_FAMILYL4) {
        FamilyL4Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FAMILYL3) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_FOODWELCOME) {
        FoodWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FOODL1) }
        )
    }
    composable(Routes.LESSON_FOODL1) {
        FoodL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FOODWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FOODL2) }
        )
    }
    composable(Routes.LESSON_FOODL2) {
        FoodL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FOODL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_FOODL3) }
        )
    }
    composable(Routes.LESSON_FOODL3) {
        FoodL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_FOODL2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_WHEREWELCOME) {
        WhereWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_WHEREL1) }
        )
    }
    composable(Routes.LESSON_WHEREL1) {
        WhereL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_WHEREWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_WHEREL2) }
        )
    }
    composable(Routes.LESSON_WHEREL2) {
        WhereL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_WHEREL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_WHEREL3) }
        )
    }
    composable(Routes.LESSON_WHEREL3) {
        WhereL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_WHEREL2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_HOMEWELCOME) {
        HomeWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HOMEL1) }
        )
    }
    composable(Routes.LESSON_HOMEL1) {
        HomeL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HOMEWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HOMEL2) }
        )
    }
    composable(Routes.LESSON_HOMEL2) {
        HomeL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HOMEL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HOMEL3) }
        )
    }
    composable(Routes.LESSON_HOMEL3) {
        HomeL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_HOMEL2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_DAILYWELCOME) {
        DailyWelcomeScreen(
            navigateBack = { appState.navController.navigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_DAILYL1) }
        )
    }
    composable(Routes.LESSON_DAILYL1) {
        DailyL1Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_DAILYWELCOME) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_DAILYL2) }
        )
    }
    composable(Routes.LESSON_DAILYL2) {
        DailyL2Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_DAILYL1) },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_DAILYL3) }
        )
    }
    composable(Routes.LESSON_DAILYL3) {
        DailyL3Screen(
            navigateBack = { appState.navController.navigate(Routes.LESSON_DAILYL2) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }


    composable(Routes.HIRAGANA_CHART) {
        HiraganaChartScreen(
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            onCharClick         = { char ->
                when (char) {
                    "あ" -> appState.navController.navigate(Routes.A_HIRAGANA_MEMORY)
                    "い" -> appState.navController.navigate(Routes.I_HIRAGANA_MEMORY)
                    "う" -> appState.navController.navigate(Routes.U_HIRAGANA_MEMORY)
                    "え" -> appState.navController.navigate(Routes.E_HIRAGANA_MEMORY)
                    "お" -> appState.navController.navigate(Routes.O_HIRAGANA_MEMORY)
                    "か" -> appState.navController.navigate(Routes.KA_HIRAGANA_MEMORY)
                    "き" -> appState.navController.navigate(Routes.KI_HIRAGANA_MEMORY)
                    "く" -> appState.navController.navigate(Routes.KU_HIRAGANA_MEMORY)
                    "け" -> appState.navController.navigate(Routes.KE_HIRAGANA_MEMORY)
                    "こ" -> appState.navController.navigate(Routes.KO_HIRAGANA_MEMORY)
                    "さ" -> appState.navController.navigate(Routes.SA_HIRAGANA_MEMORY)
                    "し" -> appState.navController.navigate(Routes.SHI_HIRAGANA_MEMORY)
                    "す" -> appState.navController.navigate(Routes.SU_HIRAGANA_MEMORY)
                    "せ" -> appState.navController.navigate(Routes.SE_HIRAGANA_MEMORY)
                    "そ" -> appState.navController.navigate(Routes.SO_HIRAGANA_MEMORY)
                    "た" -> appState.navController.navigate(Routes.TA_HIRAGANA_MEMORY)
                    "ち" -> appState.navController.navigate(Routes.CHI_HIRAGANA_MEMORY)
                    "つ" -> appState.navController.navigate(Routes.TSU_HIRAGANA_MEMORY)
                    "て" -> appState.navController.navigate(Routes.TE_HIRAGANA_MEMORY)
                    "と" -> appState.navController.navigate(Routes.TO_HIRAGANA_MEMORY)
                    "な" -> appState.navController.navigate(Routes.NA_HIRAGANA_MEMORY)
                    "に" -> appState.navController.navigate(Routes.NI_HIRAGANA_MEMORY)
                    "ぬ" -> appState.navController.navigate(Routes.NU_HIRAGANA_MEMORY)
                    "ね" -> appState.navController.navigate(Routes.NE_HIRAGANA_MEMORY)
                    "の" -> appState.navController.navigate(Routes.NO_HIRAGANA_MEMORY)
                    "は" -> appState.navController.navigate(Routes.HA_HIRAGANA_MEMORY)
                    "ひ" -> appState.navController.navigate(Routes.HI_HIRAGANA_MEMORY)
                    "ふ" -> appState.navController.navigate(Routes.FU_HIRAGANA_MEMORY)
                    "へ" -> appState.navController.navigate(Routes.HE_HIRAGANA_MEMORY)
                    "ほ" -> appState.navController.navigate(Routes.HO_HIRAGANA_MEMORY)
                    "ま" -> appState.navController.navigate(Routes.MA_HIRAGANA_MEMORY)
                    "み" -> appState.navController.navigate(Routes.MI_HIRAGANA_MEMORY)
                    "む" -> appState.navController.navigate(Routes.MU_HIRAGANA_MEMORY)
                    "め" -> appState.navController.navigate(Routes.ME_HIRAGANA_MEMORY)
                    "も" -> appState.navController.navigate(Routes.MO_HIRAGANA_MEMORY)
                    "や" -> appState.navController.navigate(Routes.YA_HIRAGANA_MEMORY)
                    "ゆ" -> appState.navController.navigate(Routes.YU_HIRAGANA_MEMORY)
                    "よ" -> appState.navController.navigate(Routes.YO_HIRAGANA_MEMORY)
                    "ら" -> appState.navController.navigate(Routes.RA_HIRAGANA_MEMORY)
                    "り" -> appState.navController.navigate(Routes.RI_HIRAGANA_MEMORY)
                    "る" -> appState.navController.navigate(Routes.RU_HIRAGANA_MEMORY)
                    "れ" -> appState.navController.navigate(Routes.RE_HIRAGANA_MEMORY)
                    "ろ" -> appState.navController.navigate(Routes.RO_HIRAGANA_MEMORY)
                    "わ" -> appState.navController.navigate(Routes.WA_HIRAGANA_MEMORY)
                    "を" -> appState.navController.navigate(Routes.WO_HIRAGANA_MEMORY)
                    "ん" -> appState.navController.navigate(Routes.N_HIRAGANA_MEMORY)
                }
            }
        )
    }

    composable(Routes.KATAKANA_CHART) {
        KatakanaChartScreen(
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            onCharClick = { char ->
                when (char) {
                    "ア" -> appState.navController.navigate(Routes.A_KATAKANA_MEMORY)
                    "イ" -> appState.navController.navigate(Routes.I_KATAKANA_MEMORY)
                    "ウ" -> appState.navController.navigate(Routes.U_KATAKANA_MEMORY)
                    "エ" -> appState.navController.navigate(Routes.E_KATAKANA_MEMORY)
                    "オ" -> appState.navController.navigate(Routes.O_KATAKANA_MEMORY)
                    "カ" -> appState.navController.navigate(Routes.KA_KATAKANA_MEMORY)
                    "キ" -> appState.navController.navigate(Routes.KI_KATAKANA_MEMORY)
                    "ク" -> appState.navController.navigate(Routes.KU_KATAKANA_MEMORY)
                    "ケ" -> appState.navController.navigate(Routes.KE_KATAKANA_MEMORY)
                    "コ" -> appState.navController.navigate(Routes.KO_KATAKANA_MEMORY)
                    "サ" -> appState.navController.navigate(Routes.SA_KATAKANA_MEMORY)
                    "シ" -> appState.navController.navigate(Routes.SHI_KATAKANA_MEMORY)
                    "ス" -> appState.navController.navigate(Routes.SU_KATAKANA_MEMORY)
                    "セ" -> appState.navController.navigate(Routes.SE_KATAKANA_MEMORY)
                    "ソ" -> appState.navController.navigate(Routes.SO_KATAKANA_MEMORY)
                    "タ" -> appState.navController.navigate(Routes.TA_KATAKANA_MEMORY)
                    "チ" -> appState.navController.navigate(Routes.CHI_KATAKANA_MEMORY)
                    "ツ" -> appState.navController.navigate(Routes.TSU_KATAKANA_MEMORY)
                    "テ" -> appState.navController.navigate(Routes.TE_KATAKANA_MEMORY)
                    "ト" -> appState.navController.navigate(Routes.TO_KATAKANA_MEMORY)
                    "ナ" -> appState.navController.navigate(Routes.NA_KATAKANA_MEMORY)
                    "ニ" -> appState.navController.navigate(Routes.NI_KATAKANA_MEMORY)
                    "ヌ" -> appState.navController.navigate(Routes.NU_KATAKANA_MEMORY)
                    "ネ" -> appState.navController.navigate(Routes.NE_KATAKANA_MEMORY)
                    "ノ" -> appState.navController.navigate(Routes.NO_KATAKANA_MEMORY)
                    "ハ" -> appState.navController.navigate(Routes.HA_KATAKANA_MEMORY)
                    "ヒ" -> appState.navController.navigate(Routes.HI_KATAKANA_MEMORY)
                    "フ" -> appState.navController.navigate(Routes.FU_KATAKANA_MEMORY)
                    "ヘ" -> appState.navController.navigate(Routes.HE_KATAKANA_MEMORY)
                    "ホ" -> appState.navController.navigate(Routes.HO_KATAKANA_MEMORY)
                    "マ" -> appState.navController.navigate(Routes.MA_KATAKANA_MEMORY)
                    "ミ" -> appState.navController.navigate(Routes.MI_KATAKANA_MEMORY)
                    "ム" -> appState.navController.navigate(Routes.MU_KATAKANA_MEMORY)
                    "メ" -> appState.navController.navigate(Routes.ME_KATAKANA_MEMORY)
                    "モ" -> appState.navController.navigate(Routes.MO_KATAKANA_MEMORY)
                    "ヤ" -> appState.navController.navigate(Routes.YA_KATAKANA_MEMORY)
                    "ユ" -> appState.navController.navigate(Routes.YU_KATAKANA_MEMORY)
                    "ヨ" -> appState.navController.navigate(Routes.YO_KATAKANA_MEMORY)
                    "ラ" -> appState.navController.navigate(Routes.RA_KATAKANA_MEMORY)
                    "リ" -> appState.navController.navigate(Routes.RI_KATAKANA_MEMORY)
                    "ル" -> appState.navController.navigate(Routes.RU_KATAKANA_MEMORY)
                    "レ" -> appState.navController.navigate(Routes.RE_KATAKANA_MEMORY)
                    "ロ" -> appState.navController.navigate(Routes.RO_KATAKANA_MEMORY)
                    "ワ" -> appState.navController.navigate(Routes.WA_KATAKANA_MEMORY)
                    "ヲ" -> appState.navController.navigate(Routes.WO_KATAKANA_MEMORY)
                    "ン" -> appState.navController.navigate(Routes.N_KATAKANA_MEMORY)
                }
            }
        )
    }

    composable(Routes.KANJI_CHART) {
        KanjiChartScreen(
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            onCharClick = { char ->
                when (char) {
                    // Food
                    "魚" -> appState.navController.navigate(Routes.SAKANA_KANJI_MEMORY)
                    "肉" -> appState.navController.navigate(Routes.NIKU_KANJI_MEMORY)
                    "水" -> appState.navController.navigate(Routes.MIZU_KANJI_MEMORY)
                    "食" -> appState.navController.navigate(Routes.TABEMASU_KANJI_MEMORY)

                    // Home
                    "大" -> appState.navController.navigate(Routes.OOKI_KANJI_MEMORY)
                    "小" -> appState.navController.navigate(Routes.CHIISAI_KANJI_MEMORY)

                    // Life
                    "時" -> appState.navController.navigate(Routes.ICHIJI_KANJI_MEMORY)
                    "半" -> appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MEMORY)
                    "月" -> appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MEMORY)
                    "火" -> appState.navController.navigate(Routes.KAYOUBI_KANJI_MEMORY)
                    "水" -> appState.navController.navigate(Routes.SUIYOUBI_KANJI_MEMORY) //same char but diff meaning
                    "木" -> appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MEMORY)
                    "金" -> appState.navController.navigate(Routes.KINYOUBI_KANJI_MEMORY)
                    "土" -> appState.navController.navigate(Routes.DOYOUBI_KANJI_MEMORY)
                    "日" -> appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MEMORY)
                }
            }
        )
    }

    composable(Routes.A_HIRAGANA_MEMORY) {
        AHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE)}
        )
    }

    // Mnemonic screen
    composable(Routes.A_HIRAGANA_MNEMONIC) {
        AHiraganaMnemonicScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    // Stroke‑order screen
    composable(Routes.A_HIRAGANA_STROKE) {
        AHiraganaStrokeScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    // Write‑it screen
    composable(Routes.A_HIRAGANA_WRITE) {
        AHiraganaWriteScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_MEMORY) {
        IHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.I_HIRAGANA_MNEMONIC) {
        IHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_STROKE) {
        IHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_WRITE) {
        IHiraganaWriteScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_MEMORY) {
        UHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.U_HIRAGANA_MNEMONIC) {
        UHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_STROKE) {
        UHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_WRITE) {
        UHiraganaWriteScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_MEMORY) {
        EHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.E_HIRAGANA_MNEMONIC) {
        EHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_STROKE) {
        EHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_WRITE) {
        EHiraganaWriteScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_MEMORY) {
        OHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.O_HIRAGANA_MNEMONIC) {
        OHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_STROKE) {
        OHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_WRITE) {
        OHiraganaWriteScreen(
            navigateBack        = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_MEMORY) {
        KaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_MNEMONIC) {
        KaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_STROKE) {
        KaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_WRITE) {
        KaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_MEMORY) {
        KiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_MNEMONIC) {
        KiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_STROKE) {
        KiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_WRITE) {
        KiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_MEMORY) {
        KuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_MNEMONIC) {
        KuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_STROKE) {
        KuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_WRITE) {
        KuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_MEMORY) {
        KeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_MNEMONIC) {
        KeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_STROKE) {
        KeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_WRITE) {
        KeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_MEMORY) {
        KoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_MNEMONIC) {
        KoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_STROKE) {
        KoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_WRITE) {
        KoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_MEMORY) {
        SaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_MNEMONIC) {
        SaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_STROKE) {
        SaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_WRITE) {
        SaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_MEMORY) {
        ShiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_MNEMONIC) {
        ShiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_STROKE) {
        ShiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_WRITE) {
        ShiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_MEMORY) {
        SuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_MNEMONIC) {
        SuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_STROKE) {
        SuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_WRITE) {
        SuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_MEMORY) {
        SeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_MNEMONIC) {
        SeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_STROKE) {
        SeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_WRITE) {
        SeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_MEMORY) {
        SoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_MNEMONIC) {
        SoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_STROKE) {
        SoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_WRITE) {
        SoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_MEMORY) {
        TaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_MNEMONIC) {
        TaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_STROKE) {
        TaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_WRITE) {
        TaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_MEMORY) {
        ChiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_MNEMONIC) {
        ChiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_STROKE) {
        ChiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_WRITE) {
        ChiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_MEMORY) {
        TsuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_MNEMONIC) {
        TsuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_STROKE) {
        TsuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_WRITE) {
        TsuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_MEMORY) {
        TeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_MNEMONIC) {
        TeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_STROKE) {
        TeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_WRITE) {
        TeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_MEMORY) {
        ToHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_MNEMONIC) {
        ToHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_STROKE) {
        ToHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_WRITE) {
        ToHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_MEMORY) {
        NaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_MNEMONIC) {
        NaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_STROKE) {
        NaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_WRITE) {
        NaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_MEMORY) {
        NiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_MNEMONIC) {
        NiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_STROKE) {
        NiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_WRITE) {
        NiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_MEMORY) {
        NuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_MNEMONIC) {
        NuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_STROKE) {
        NuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_WRITE) {
        NuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_MEMORY) {
        NeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_MNEMONIC) {
        NeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_STROKE) {
        NeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_WRITE) {
        NeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_MEMORY) {
        NoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_MNEMONIC) {
        NoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_STROKE) {
        NoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_WRITE) {
        NoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_MEMORY) {
        HaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_MNEMONIC) {
        HaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_STROKE) {
        HaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_WRITE) {
        HaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_MEMORY) {
        HiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_MNEMONIC) {
        HiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_STROKE) {
        HiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_WRITE) {
        HiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_MEMORY) {
        FuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_MNEMONIC) {
        FuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_STROKE) {
        FuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_WRITE) {
        FuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_MEMORY) {
        HeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_MNEMONIC) {
        HeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_STROKE) {
        HeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_WRITE) {
        HeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_MEMORY) {
        HoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_MNEMONIC) {
        HoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_STROKE) {
        HoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_WRITE) {
        HoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_MEMORY) {
        MaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_MNEMONIC) {
        MaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_STROKE) {
        MaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_WRITE) {
        MaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_MEMORY) {
        MiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_MNEMONIC) {
        MiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_STROKE) {
        MiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_WRITE) {
        MiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_MEMORY) {
        MuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_MNEMONIC) {
        MuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_STROKE) {
        MuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_WRITE) {
        MuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_MEMORY) {
        MeHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_MNEMONIC) {
        MeHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_STROKE) {
        MeHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_WRITE) {
        MeHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_MEMORY) {
        MoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_MNEMONIC) {
        MoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_STROKE) {
        MoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_WRITE) {
        MoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_MEMORY) {
        YaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_MNEMONIC) {
        YaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_STROKE) {
        YaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_WRITE) {
        YaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_MEMORY) {
        YuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_MNEMONIC) {
        YuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_STROKE) {
        YuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_WRITE) {
        YuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_MEMORY) {
        YoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_MNEMONIC) {
        YoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_STROKE) {
        YoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_WRITE) {
        YoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_MEMORY) {
        RaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_MNEMONIC) {
        RaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_STROKE) {
        RaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_WRITE) {
        RaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_MEMORY) {
        RiHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_MNEMONIC) {
        RiHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_STROKE) {
        RiHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_WRITE) {
        RiHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_MEMORY) {
        RuHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_MNEMONIC) {
        RuHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_STROKE) {
        RuHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_WRITE) {
        RuHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_MEMORY) {
        ReHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_MNEMONIC) {
        ReHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_STROKE) {
        ReHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_WRITE) {
        ReHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_MEMORY) {
        RoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_MNEMONIC) {
        RoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_STROKE) {
        RoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_WRITE) {
        RoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_MEMORY) {
        WaHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_MNEMONIC) {
        WaHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_STROKE) {
        WaHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_WRITE) {
        WaHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_MEMORY) {
        WoHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_MNEMONIC) {
        WoHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_STROKE) {
        WoHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_WRITE) {
        WoHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_MEMORY) {
        NHiraganaMemoryScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_MNEMONIC) {
        NHiraganaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_STROKE) {
        NHiraganaStrokeScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_WRITE) {
        NHiraganaWriteScreen(
            navigateBack = { appState.navigate(Routes.HIRAGANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    //Katakana
    composable(Routes.A_KATAKANA_MEMORY) {
        AKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_MNEMONIC) {
        AKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_STROKE) {
        AKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_WRITE) {
        AKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_MEMORY) {
        IKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_MNEMONIC) {
        IKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_STROKE) {
        IKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_WRITE) {
        IKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_MEMORY) {
        UKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_MNEMONIC) {
        UKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_STROKE) {
        UKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_WRITE) {
        UKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_MEMORY) {
        EKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_MNEMONIC) {
        EKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_STROKE) {
        EKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_WRITE) {
        EKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_MEMORY) {
        OKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_MNEMONIC) {
        OKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_STROKE) {
        OKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_WRITE) {
        OKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_MEMORY) {
        KaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_MNEMONIC) {
        KaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_STROKE) {
        KaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_WRITE) {
        KaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_MEMORY) {
        KiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_MNEMONIC) {
        KiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_STROKE) {
        KiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_WRITE) {
        KiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_MEMORY) {
        KuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_MNEMONIC) {
        KuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_STROKE) {
        KuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_WRITE) {
        KuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_MEMORY) {
        KeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_MNEMONIC) {
        KeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_STROKE) {
        KeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_WRITE) {
        KeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_MEMORY) {
        KoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_MNEMONIC) {
        KoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_STROKE) {
        KoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_WRITE) {
        KoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_MEMORY) {
        SaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_MNEMONIC) {
        SaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_STROKE) {
        SaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_WRITE) {
        SaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_MEMORY) {
        ShiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_MNEMONIC) {
        ShiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_STROKE) {
        ShiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_WRITE) {
        ShiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_MEMORY) {
        SuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_MNEMONIC) {
        SuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_STROKE) {
        SuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_WRITE) {
        SuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_MEMORY) {
        SeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_MNEMONIC) {
        SeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_STROKE) {
        SeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_WRITE) {
        SeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_MEMORY) {
        SoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_MNEMONIC) {
        SoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_STROKE) {
        SoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_WRITE) {
        SoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_MEMORY) {
        TaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_MNEMONIC) {
        TaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_STROKE) {
        TaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_WRITE) {
        TaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_MEMORY) {
        ChiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_MNEMONIC) {
        ChiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_STROKE) {
        ChiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_WRITE) {
        ChiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_MEMORY) {
        TsuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_MNEMONIC) {
        TsuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_STROKE) {
        TsuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_WRITE) {
        TsuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_MEMORY) {
        TeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_MNEMONIC) {
        TeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_STROKE) {
        TeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_WRITE) {
        TeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_MEMORY) {
        ToKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_MNEMONIC) {
        ToKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_STROKE) {
        ToKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_WRITE) {
        ToKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_MEMORY) {
        NaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_MNEMONIC) {
        NaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_STROKE) {
        NaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_WRITE) {
        NaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_MEMORY) {
        NiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_MNEMONIC) {
        NiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_STROKE) {
        NiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_WRITE) {
        NiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_MEMORY) {
        NuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_MNEMONIC) {
        NuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_STROKE) {
        NuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_WRITE) {
        NuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_MEMORY) {
        NeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_MNEMONIC) {
        NeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_STROKE) {
        NeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_WRITE) {
        NeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_MEMORY) {
        NoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_MNEMONIC) {
        NoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_STROKE) {
        NoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_WRITE) {
        NoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_MEMORY) {
        HaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_MNEMONIC) {
        HaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_STROKE) {
        HaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_WRITE) {
        HaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_MEMORY) {
        HiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_MNEMONIC) {
        HiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_STROKE) {
        HiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_WRITE) {
        HiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_MEMORY) {
        FuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_MNEMONIC) {
        FuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_STROKE) {
        FuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_WRITE) {
        FuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_MEMORY) {
        HeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_MNEMONIC) {
        HeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_STROKE) {
        HeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_WRITE) {
        HeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_MEMORY) {
        HoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_MNEMONIC) {
        HoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_STROKE) {
        HoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_WRITE) {
        HoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_MEMORY) {
        MaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_MNEMONIC) {
        MaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_STROKE) {
        MaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_WRITE) {
        MaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_MEMORY) {
        MiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_MNEMONIC) {
        MiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_STROKE) {
        MiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_WRITE) {
        MiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_MEMORY) {
        MuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_MNEMONIC) {
        MuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_STROKE) {
        MuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_WRITE) {
        MuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_MEMORY) {
        MeKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_MNEMONIC) {
        MeKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_STROKE) {
        MeKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_WRITE) {
        MeKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_MEMORY) {
        MoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_MNEMONIC) {
        MoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_STROKE) {
        MoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_WRITE) {
        MoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_MEMORY) {
        YaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_MNEMONIC) {
        YaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_STROKE) {
        YaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_WRITE) {
        YaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_MEMORY) {
        YuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_MNEMONIC) {
        YuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_STROKE) {
        YuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_WRITE) {
        YuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_MEMORY) {
        YoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_MNEMONIC) {
        YoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_STROKE) {
        YoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_WRITE) {
        YoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_MEMORY) {
        RaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_MNEMONIC) {
        RaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_STROKE) {
        RaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_WRITE) {
        RaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_MEMORY) {
        RiKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_MNEMONIC) {
        RiKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_STROKE) {
        RiKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_WRITE) {
        RiKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_MEMORY) {
        RuKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_MNEMONIC) {
        RuKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_STROKE) {
        RuKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_WRITE) {
        RuKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_MEMORY) {
        ReKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_MNEMONIC) {
        ReKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_STROKE) {
        ReKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_WRITE) {
        ReKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_MEMORY) {
        RoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_MNEMONIC) {
        RoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_STROKE) {
        RoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_WRITE) {
        RoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_MEMORY) {
        WaKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_MNEMONIC) {
        WaKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_STROKE) {
        WaKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_WRITE) {
        WaKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_MEMORY) {
        WoKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_MNEMONIC) {
        WoKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_STROKE) {
        WoKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_WRITE) {
        WoKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_MEMORY) {
        NKatakanaMemoryScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_MNEMONIC) {
        NKatakanaMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_STROKE) {
        NKatakanaStrokeScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_WRITE) {
        NKatakanaWriteScreen(
            navigateBack = { appState.navigate(Routes.KATAKANA_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    //Kanji
    composable(Routes.SAKANA_KANJI_MEMORY) {
        SakanaKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_MNEMONIC) {
        SakanaKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_STROKE) {
        SakanaKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_WRITE) {
        SakanaKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_MEMORY) {
        NikuKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_MNEMONIC) {
        NikuKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_STROKE) {
        NikuKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_WRITE) {
        NikuKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_MEMORY) {
        MizuKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_MNEMONIC) {
        MizuKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_STROKE) {
        MizuKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_WRITE) {
        MizuKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_MEMORY) {
        TabemasuKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_MNEMONIC) {
        TabemasuKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_STROKE) {
        TabemasuKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_WRITE) {
        TabemasuKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_MEMORY) {
        OokiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_MNEMONIC) {
        OokiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_STROKE) {
        OokiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_WRITE) {
        OokiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_MEMORY) {
        ChiisaiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_MNEMONIC) {
        ChiisaiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_STROKE) {
        ChiisaiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_WRITE) {
        ChiisaiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_MEMORY) {
        IchijiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_MNEMONIC) {
        IchijiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_STROKE) {
        IchijiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_WRITE) {
        IchijiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_MEMORY) {
        IchijihanKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_MNEMONIC) {
        IchijihanKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_STROKE) {
        IchijihanKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_WRITE) {
        IchijihanKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_MEMORY) {
        GetsuyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_MNEMONIC) {
        GetsuyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_STROKE) {
        GetsuyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_WRITE) {
        GetsuyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_MEMORY) {
        KayoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_MNEMONIC) {
        KayoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_STROKE) {
        KayoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_WRITE) {
        KayoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_MEMORY) {
        SuiyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_MNEMONIC) {
        SuiyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_STROKE) {
        SuiyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_WRITE) {
        SuiyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_MEMORY) {
        MokuyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_MNEMONIC) {
        MokuyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_STROKE) {
        MokuyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_WRITE) {
        MokuyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_MEMORY) {
        KinyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_MNEMONIC) {
        KinyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_STROKE) {
        KinyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_WRITE) {
        KinyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_MEMORY) {
        DoyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_MNEMONIC) {
        DoyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_STROKE) {
        DoyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_WRITE) {
        DoyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_MEMORY) {
        NichiyoubiKanjiMemoryScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_MNEMONIC) {
        NichiyoubiKanjiMnemonicScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_STROKE) {
        NichiyoubiKanjiStrokeScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_WRITE) {
        NichiyoubiKanjiWriteScreen(
            navigateBack = { appState.navigate(Routes.KANJI_CHART) },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

}