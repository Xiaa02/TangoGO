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
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaQ1Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaTableScreen
import com.example.tangogo.ui.screens.dashboard.DashboardScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaQ2Screen
import com.example.tangogo.ui.screens.login.LoginScreen
import com.example.tangogo.ui.screens.lessonHiragana.Hiragana101Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaL1Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaL2Screen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaSpeakScreen
import com.example.tangogo.ui.screens.lessonHiragana.HiraganaTableScreen
import com.example.tangogo.ui.screens.lessonHiragana.LessonCompleteScreen
import com.example.tangogo.ui.screens.lessonKatakana.Katakana101Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaL1Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaL2Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaQ1Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaQ2Screen
import com.example.tangogo.ui.screens.lessonKatakana.KatakanaSpeakScreen
import com.example.tangogo.ui.screens.lessonHello.HelloL1Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL2Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL3Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL4Screen
import com.example.tangogo.ui.screens.lessonHello.HelloL5Screen
import com.example.tangogo.ui.screens.lessonHello.HelloQ1Screen
import com.example.tangogo.ui.screens.lessonHello.HelloQ2Screen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.AHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.ChiHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.HiraganaChartScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.IHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.UHiraganaWriteScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.EHiraganaWriteScreen
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
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.KaHiraganaMnemonicScreen
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
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaMemoryScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaMnemonicScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaStrokeScreen
import com.example.tangogo.ui.screens.memoryHiragana.OHiraganaWriteScreen
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

    composable(Routes.LESSON_COMPLETE) {
        LessonCompleteScreen(
            navigateToDashboard = { appState.navController.navigate(Routes.DASHBOARD) }
        )
    }

    composable(Routes.LESSON_HIRAGANA101) {
        Hiragana101Screen(
            navigateBack = { appState.popUp() },
            //navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToNext = { appState.navController.navigate(Routes.HIRAGANA_TABLE) }
        )
    }

    composable(Routes.HIRAGANA_TABLE) {
        HiraganaTableScreen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAL1) }
        )
    }

    composable(Routes.LESSON_HIRAGANAL1) {
        HiraganaL1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAL2) }
        )
    }
    composable(Routes.LESSON_HIRAGANAL2) {
        HiraganaL2Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ1) }
        )
    }

    composable(Routes.LESSON_HIRAGANAQ1) {
        HiraganaQ1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANAQ2) }
        )
    }

    composable(Routes.LESSON_HIRAGANAQ2) {
        HiraganaQ2Screen(
            navigateBack = { appState.navController.popBackStack() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HIRAGANASPEAK) }
        )
    }

    composable(Routes.LESSON_HIRAGANASPEAK) {
        HiraganaSpeakScreen(
            navigateBack = { appState.navController.popBackStack() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_KATAKANA101) {
        Katakana101Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.KATAKANA_TABLE) }
        )
    }

    composable(Routes.KATAKANA_TABLE) {
        KatakanaTableScreen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAL1) }
        )
    }

    composable(Routes.LESSON_KATAKANAL1) {
        KatakanaL1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAL2) }
        )
    }
    composable(Routes.LESSON_KATAKANAL2) {
        KatakanaL2Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAQ1) }
        )
    }

    composable(Routes.LESSON_KATAKANAQ1) {
        KatakanaQ1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANAQ2) }
        )
    }

    composable(Routes.LESSON_KATAKANAQ2) {
        KatakanaQ2Screen(
            navigateBack = { appState.navController.popBackStack() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_KATAKANASPEAK) }
        )
    }

    composable(Routes.LESSON_KATAKANASPEAK) {
        KatakanaSpeakScreen(
            navigateBack = { appState.navController.popBackStack() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToLessonComplete = { appState.navController.navigate(Routes.LESSON_COMPLETE) }
        )
    }

    composable(Routes.LESSON_HELLOL1) {
        HelloL1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL2) }
        )
    }

    composable(Routes.LESSON_HELLOL2) {
        HelloL2Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL3) }
        )
    }

    composable(Routes.LESSON_HELLOL3) {
        HelloL3Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL4) }
        )
    }

    composable(Routes.LESSON_HELLOL4) {
        HelloL4Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOL5) }
        )
    }


    composable(Routes.LESSON_HELLOL5) {
        HelloL5Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOQ1) }
        )
    }

    composable(Routes.LESSON_HELLOQ1) {
        HelloQ1Screen(
            navigateBack = { appState.popUp() },
            navigateToNext = { appState.navController.navigate(Routes.LESSON_HELLOQ2) }
        )
    }

    composable(Routes.LESSON_HELLOQ2) {
        HelloQ2Screen(
            navigateBack = { appState.popUp() },
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
            navigateBack = { appState.popUp() },
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
            navigateBack        = { appState.popUp() },
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
            navigateBack        = { appState.popUp() },
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
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.A_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.A_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.A_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_MEMORY) {
        IHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.I_HIRAGANA_MNEMONIC) {
        IHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_STROKE) {
        IHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.I_HIRAGANA_WRITE) {
        IHiraganaWriteScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.I_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.I_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.I_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_MEMORY) {
        UHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.U_HIRAGANA_MNEMONIC) {
        UHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_STROKE) {
        UHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.U_HIRAGANA_WRITE) {
        UHiraganaWriteScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.U_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.U_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.U_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_MEMORY) {
        EHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.E_HIRAGANA_MNEMONIC) {
        EHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_STROKE) {
        EHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.E_HIRAGANA_WRITE) {
        EHiraganaWriteScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.E_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.E_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.E_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_MEMORY) {
        OHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE)}
        )
    }

    composable(Routes.O_HIRAGANA_MNEMONIC) {
        OHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_HIRAGANA_MEMORY) },  // if want buttons here, otherwise omit
            onStrokeClick = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_STROKE) {
        OHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.O_HIRAGANA_WRITE) {
        OHiraganaWriteScreen(
            navigateBack        = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick     = { appState.navController.navigate(Routes.O_HIRAGANA_MNEMONIC) },
            onStrokeClick         = { appState.navController.navigate(Routes.O_HIRAGANA_STROKE) },
            onWriteClick        = { appState.navController.navigate(Routes.O_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_MEMORY) {
        KaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_MNEMONIC) {
        KaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_STROKE) {
        KaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KA_HIRAGANA_WRITE) {
        KaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_MEMORY) {
        KiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_MNEMONIC) {
        KiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_STROKE) {
        KiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KI_HIRAGANA_WRITE) {
        KiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_MEMORY) {
        KuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_MNEMONIC) {
        KuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_STROKE) {
        KuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KU_HIRAGANA_WRITE) {
        KuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_MEMORY) {
        KeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_MNEMONIC) {
        KeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_STROKE) {
        KeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KE_HIRAGANA_WRITE) {
        KeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_MEMORY) {
        KoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_MNEMONIC) {
        KoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_STROKE) {
        KoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.KO_HIRAGANA_WRITE) {
        KoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_MEMORY) {
        SaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_MNEMONIC) {
        SaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_STROKE) {
        SaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SA_HIRAGANA_WRITE) {
        SaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_MEMORY) {
        ShiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_MNEMONIC) {
        ShiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_STROKE) {
        ShiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SHI_HIRAGANA_WRITE) {
        ShiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_MEMORY) {
        SuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_MNEMONIC) {
        SuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_STROKE) {
        SuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SU_HIRAGANA_WRITE) {
        SuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_MEMORY) {
        SeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_MNEMONIC) {
        SeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_STROKE) {
        SeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SE_HIRAGANA_WRITE) {
        SeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_MEMORY) {
        SoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_MNEMONIC) {
        SoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_STROKE) {
        SoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.SO_HIRAGANA_WRITE) {
        SoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_MEMORY) {
        TaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_MNEMONIC) {
        TaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_STROKE) {
        TaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TA_HIRAGANA_WRITE) {
        TaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_MEMORY) {
        ChiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_MNEMONIC) {
        ChiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_STROKE) {
        ChiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.CHI_HIRAGANA_WRITE) {
        ChiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_MEMORY) {
        TsuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_MNEMONIC) {
        TsuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_STROKE) {
        TsuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TSU_HIRAGANA_WRITE) {
        TsuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_MEMORY) {
        TeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_MNEMONIC) {
        TeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_STROKE) {
        TeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TE_HIRAGANA_WRITE) {
        TeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_MEMORY) {
        ToHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_MNEMONIC) {
        ToHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_STROKE) {
        ToHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.TO_HIRAGANA_WRITE) {
        ToHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_MEMORY) {
        NaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_MNEMONIC) {
        NaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_STROKE) {
        NaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NA_HIRAGANA_WRITE) {
        NaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_MEMORY) {
        NiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_MNEMONIC) {
        NiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_STROKE) {
        NiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NI_HIRAGANA_WRITE) {
        NiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_MEMORY) {
        NuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_MNEMONIC) {
        NuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_STROKE) {
        NuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NU_HIRAGANA_WRITE) {
        NuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_MEMORY) {
        NeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_MNEMONIC) {
        NeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_STROKE) {
        NeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NE_HIRAGANA_WRITE) {
        NeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_MEMORY) {
        NoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_MNEMONIC) {
        NoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_STROKE) {
        NoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.NO_HIRAGANA_WRITE) {
        NoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_MEMORY) {
        HaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_MNEMONIC) {
        HaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_STROKE) {
        HaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HA_HIRAGANA_WRITE) {
        HaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_MEMORY) {
        HiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_MNEMONIC) {
        HiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_STROKE) {
        HiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HI_HIRAGANA_WRITE) {
        HiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_MEMORY) {
        FuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_MNEMONIC) {
        FuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_STROKE) {
        FuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.FU_HIRAGANA_WRITE) {
        FuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_MEMORY) {
        HeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_MNEMONIC) {
        HeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_STROKE) {
        HeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HE_HIRAGANA_WRITE) {
        HeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_MEMORY) {
        HoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_MNEMONIC) {
        HoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_STROKE) {
        HoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.HO_HIRAGANA_WRITE) {
        HoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_MEMORY) {
        MaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_MNEMONIC) {
        MaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_STROKE) {
        MaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MA_HIRAGANA_WRITE) {
        MaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_MEMORY) {
        MiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_MNEMONIC) {
        MiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_STROKE) {
        MiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MI_HIRAGANA_WRITE) {
        MiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_MEMORY) {
        MuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_MNEMONIC) {
        MuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_STROKE) {
        MuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MU_HIRAGANA_WRITE) {
        MuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_MEMORY) {
        MeHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_MNEMONIC) {
        MeHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_STROKE) {
        MeHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.ME_HIRAGANA_WRITE) {
        MeHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_MEMORY) {
        MoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_MNEMONIC) {
        MoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_STROKE) {
        MoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.MO_HIRAGANA_WRITE) {
        MoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_MEMORY) {
        YaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_MNEMONIC) {
        YaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_STROKE) {
        YaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YA_HIRAGANA_WRITE) {
        YaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_MEMORY) {
        YuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_MNEMONIC) {
        YuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_STROKE) {
        YuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YU_HIRAGANA_WRITE) {
        YuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_MEMORY) {
        YoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_MNEMONIC) {
        YoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_STROKE) {
        YoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.YO_HIRAGANA_WRITE) {
        YoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_MEMORY) {
        RaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_MNEMONIC) {
        RaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_STROKE) {
        RaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RA_HIRAGANA_WRITE) {
        RaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_MEMORY) {
        RiHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_MNEMONIC) {
        RiHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_STROKE) {
        RiHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RI_HIRAGANA_WRITE) {
        RiHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_MEMORY) {
        RuHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_MNEMONIC) {
        RuHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_STROKE) {
        RuHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RU_HIRAGANA_WRITE) {
        RuHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_MEMORY) {
        ReHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_MNEMONIC) {
        ReHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_STROKE) {
        ReHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RE_HIRAGANA_WRITE) {
        ReHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_MEMORY) {
        RoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_MNEMONIC) {
        RoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_STROKE) {
        RoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.RO_HIRAGANA_WRITE) {
        RoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_MEMORY) {
        WaHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_MNEMONIC) {
        WaHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_STROKE) {
        WaHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WA_HIRAGANA_WRITE) {
        WaHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_MEMORY) {
        WoHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_MNEMONIC) {
        WoHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_STROKE) {
        WoHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.WO_HIRAGANA_WRITE) {
        WoHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_MEMORY) {
        NHiraganaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_MNEMONIC) {
        NHiraganaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_STROKE) {
        NHiraganaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToHiraganaChart = { appState.navigate(Routes.HIRAGANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_HIRAGANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_HIRAGANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_HIRAGANA_WRITE) }
        )
    }

    composable(Routes.N_HIRAGANA_WRITE) {
        NHiraganaWriteScreen(
            navigateBack = { appState.popUp() },
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
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_MNEMONIC) {
        AKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_STROKE) {
        AKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.A_KATAKANA_WRITE) {
        AKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.A_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.A_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.A_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_MEMORY) {
        IKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_MNEMONIC) {
        IKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_STROKE) {
        IKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.I_KATAKANA_WRITE) {
        IKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.I_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.I_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.I_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_MEMORY) {
        UKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_MNEMONIC) {
        UKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_STROKE) {
        UKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.U_KATAKANA_WRITE) {
        UKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.U_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.U_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.U_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_MEMORY) {
        EKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_MNEMONIC) {
        EKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_STROKE) {
        EKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.E_KATAKANA_WRITE) {
        EKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.E_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.E_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.E_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_MEMORY) {
        OKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_MNEMONIC) {
        OKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_STROKE) {
        OKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.O_KATAKANA_WRITE) {
        OKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.O_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.O_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.O_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_MEMORY) {
        KaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_MNEMONIC) {
        KaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_STROKE) {
        KaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KA_KATAKANA_WRITE) {
        KaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_MEMORY) {
        KiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_MNEMONIC) {
        KiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_STROKE) {
        KiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KI_KATAKANA_WRITE) {
        KiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_MEMORY) {
        KuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_MNEMONIC) {
        KuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_STROKE) {
        KuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KU_KATAKANA_WRITE) {
        KuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_MEMORY) {
        KeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_MNEMONIC) {
        KeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_STROKE) {
        KeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KE_KATAKANA_WRITE) {
        KeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_MEMORY) {
        KoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_MNEMONIC) {
        KoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_STROKE) {
        KoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.KO_KATAKANA_WRITE) {
        KoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_MEMORY) {
        SaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_MNEMONIC) {
        SaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_STROKE) {
        SaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SA_KATAKANA_WRITE) {
        SaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_MEMORY) {
        ShiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_MNEMONIC) {
        ShiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_STROKE) {
        ShiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SHI_KATAKANA_WRITE) {
        ShiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_MEMORY) {
        SuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_MNEMONIC) {
        SuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_STROKE) {
        SuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SU_KATAKANA_WRITE) {
        SuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_MEMORY) {
        SeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_MNEMONIC) {
        SeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_STROKE) {
        SeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SE_KATAKANA_WRITE) {
        SeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_MEMORY) {
        SoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_MNEMONIC) {
        SoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_STROKE) {
        SoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.SO_KATAKANA_WRITE) {
        SoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_MEMORY) {
        TaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_MNEMONIC) {
        TaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_STROKE) {
        TaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TA_KATAKANA_WRITE) {
        TaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_MEMORY) {
        ChiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_MNEMONIC) {
        ChiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_STROKE) {
        ChiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.CHI_KATAKANA_WRITE) {
        ChiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_MEMORY) {
        TsuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_MNEMONIC) {
        TsuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_STROKE) {
        TsuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TSU_KATAKANA_WRITE) {
        TsuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TSU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TSU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TSU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_MEMORY) {
        TeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_MNEMONIC) {
        TeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_STROKE) {
        TeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TE_KATAKANA_WRITE) {
        TeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_MEMORY) {
        ToKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_MNEMONIC) {
        ToKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_STROKE) {
        ToKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.TO_KATAKANA_WRITE) {
        ToKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_MEMORY) {
        NaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_MNEMONIC) {
        NaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_STROKE) {
        NaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NA_KATAKANA_WRITE) {
        NaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_MEMORY) {
        NiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_MNEMONIC) {
        NiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_STROKE) {
        NiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NI_KATAKANA_WRITE) {
        NiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_MEMORY) {
        NuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_MNEMONIC) {
        NuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_STROKE) {
        NuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NU_KATAKANA_WRITE) {
        NuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_MEMORY) {
        NeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_MNEMONIC) {
        NeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_STROKE) {
        NeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NE_KATAKANA_WRITE) {
        NeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_MEMORY) {
        NoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_MNEMONIC) {
        NoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_STROKE) {
        NoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.NO_KATAKANA_WRITE) {
        NoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_MEMORY) {
        HaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_MNEMONIC) {
        HaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_STROKE) {
        HaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HA_KATAKANA_WRITE) {
        HaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_MEMORY) {
        HiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_MNEMONIC) {
        HiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_STROKE) {
        HiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HI_KATAKANA_WRITE) {
        HiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_MEMORY) {
        FuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_MNEMONIC) {
        FuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_STROKE) {
        FuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.FU_KATAKANA_WRITE) {
        FuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.FU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.FU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.FU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_MEMORY) {
        HeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_MNEMONIC) {
        HeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_STROKE) {
        HeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HE_KATAKANA_WRITE) {
        HeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_MEMORY) {
        HoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_MNEMONIC) {
        HoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_STROKE) {
        HoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.HO_KATAKANA_WRITE) {
        HoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.HO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.HO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.HO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_MEMORY) {
        MaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_MNEMONIC) {
        MaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_STROKE) {
        MaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MA_KATAKANA_WRITE) {
        MaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_MEMORY) {
        MiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_MNEMONIC) {
        MiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_STROKE) {
        MiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MI_KATAKANA_WRITE) {
        MiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_MEMORY) {
        MuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_MNEMONIC) {
        MuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_STROKE) {
        MuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MU_KATAKANA_WRITE) {
        MuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_MEMORY) {
        MeKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_MNEMONIC) {
        MeKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_STROKE) {
        MeKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.ME_KATAKANA_WRITE) {
        MeKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ME_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ME_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ME_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_MEMORY) {
        MoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_MNEMONIC) {
        MoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_STROKE) {
        MoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.MO_KATAKANA_WRITE) {
        MoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_MEMORY) {
        YaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_MNEMONIC) {
        YaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_STROKE) {
        YaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YA_KATAKANA_WRITE) {
        YaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_MEMORY) {
        YuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_MNEMONIC) {
        YuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_STROKE) {
        YuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YU_KATAKANA_WRITE) {
        YuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_MEMORY) {
        YoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_MNEMONIC) {
        YoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_STROKE) {
        YoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.YO_KATAKANA_WRITE) {
        YoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.YO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.YO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.YO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_MEMORY) {
        RaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_MNEMONIC) {
        RaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_STROKE) {
        RaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RA_KATAKANA_WRITE) {
        RaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_MEMORY) {
        RiKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_MNEMONIC) {
        RiKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_STROKE) {
        RiKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RI_KATAKANA_WRITE) {
        RiKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RI_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RI_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RI_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_MEMORY) {
        RuKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_MNEMONIC) {
        RuKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_STROKE) {
        RuKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RU_KATAKANA_WRITE) {
        RuKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RU_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RU_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RU_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_MEMORY) {
        ReKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_MNEMONIC) {
        ReKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_STROKE) {
        ReKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RE_KATAKANA_WRITE) {
        ReKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RE_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RE_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RE_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_MEMORY) {
        RoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_MNEMONIC) {
        RoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_STROKE) {
        RoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.RO_KATAKANA_WRITE) {
        RoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.RO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.RO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.RO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_MEMORY) {
        WaKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_MNEMONIC) {
        WaKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_STROKE) {
        WaKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WA_KATAKANA_WRITE) {
        WaKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WA_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WA_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WA_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_MEMORY) {
        WoKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_MNEMONIC) {
        WoKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_STROKE) {
        WoKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.WO_KATAKANA_WRITE) {
        WoKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.WO_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.WO_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.WO_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_MEMORY) {
        NKatakanaMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_MNEMONIC) {
        NKatakanaMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_STROKE) {
        NKatakanaStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKatakanaChart = { appState.navigate(Routes.KATAKANA_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.N_KATAKANA_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.N_KATAKANA_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.N_KATAKANA_WRITE) }
        )
    }

    composable(Routes.N_KATAKANA_WRITE) {
        NKatakanaWriteScreen(
            navigateBack = { appState.popUp() },
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
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_MNEMONIC) {
        SakanaKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_STROKE) {
        SakanaKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.SAKANA_KANJI_WRITE) {
        SakanaKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SAKANA_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SAKANA_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SAKANA_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_MEMORY) {
        NikuKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_MNEMONIC) {
        NikuKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_STROKE) {
        NikuKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.NIKU_KANJI_WRITE) {
        NikuKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NIKU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NIKU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NIKU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_MEMORY) {
        MizuKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_MNEMONIC) {
        MizuKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_STROKE) {
        MizuKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.MIZU_KANJI_WRITE) {
        MizuKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MIZU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MIZU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MIZU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_MEMORY) {
        TabemasuKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_MNEMONIC) {
        TabemasuKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_STROKE) {
        TabemasuKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.TABEMASU_KANJI_WRITE) {
        TabemasuKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.TABEMASU_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_MEMORY) {
        OokiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_MNEMONIC) {
        OokiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_STROKE) {
        OokiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.OOKI_KANJI_WRITE) {
        OokiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.OOKI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.OOKI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.OOKI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_MEMORY) {
        ChiisaiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_MNEMONIC) {
        ChiisaiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_STROKE) {
        ChiisaiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.CHIISAI_KANJI_WRITE) {
        ChiisaiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.CHIISAI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_MEMORY) {
        IchijiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_MNEMONIC) {
        IchijiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_STROKE) {
        IchijiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJI_KANJI_WRITE) {
        IchijiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJI_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_MEMORY) {
        IchijihanKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_MNEMONIC) {
        IchijihanKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_STROKE) {
        IchijihanKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.ICHIJIHAN_KANJI_WRITE) {
        IchijihanKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.ICHIJIHAN_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_MEMORY) {
        GetsuyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_MNEMONIC) {
        GetsuyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_STROKE) {
        GetsuyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.GETSUYOUBI_KANJI_WRITE) {
        GetsuyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.GETSUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_MEMORY) {
        KayoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_MNEMONIC) {
        KayoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_STROKE) {
        KayoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KAYOUBI_KANJI_WRITE) {
        KayoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KAYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_MEMORY) {
        SuiyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_MNEMONIC) {
        SuiyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_STROKE) {
        SuiyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.SUIYOUBI_KANJI_WRITE) {
        SuiyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.SUIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_MEMORY) {
        MokuyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_MNEMONIC) {
        MokuyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_STROKE) {
        MokuyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.MOKUYOUBI_KANJI_WRITE) {
        MokuyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.MOKUYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_MEMORY) {
        KinyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_MNEMONIC) {
        KinyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_STROKE) {
        KinyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.KINYOUBI_KANJI_WRITE) {
        KinyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.KINYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_MEMORY) {
        DoyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_MNEMONIC) {
        DoyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_STROKE) {
        DoyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.DOYOUBI_KANJI_WRITE) {
        DoyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.DOYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_MEMORY) {
        NichiyoubiKanjiMemoryScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.navigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_MNEMONIC) {
        NichiyoubiKanjiMnemonicScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MEMORY) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_STROKE) {
        NichiyoubiKanjiStrokeScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

    composable(Routes.NICHIYOUBI_KANJI_WRITE) {
        NichiyoubiKanjiWriteScreen(
            navigateBack = { appState.popUp() },
            navigateToDashboard = { appState.clearAndNavigate(Routes.DASHBOARD) },
            navigateToKanjiChart = { appState.navigate(Routes.KANJI_CHART) },
            onMnemonicClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_MNEMONIC) },
            onStrokeClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_STROKE) },
            onWriteClick = { appState.navController.navigate(Routes.NICHIYOUBI_KANJI_WRITE) }
        )
    }

}