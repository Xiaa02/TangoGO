package com.example.tangogo.ui.screens.dashboard

//import android.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tangogo.R
import com.example.tangogo.model.User
import com.example.tangogo.ui.theme.TangoGOTheme


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    clearAndNavigate: (String) -> Unit,
    openScreen: (String) -> Unit,
) {
    val user = viewModel.currentUser.collectAsStateWithLifecycle(User())
    val fullName = user.value.firstName + " " + user.value.lastName

    DashboardScreenContent(
        fullName = fullName,
        openLessonHiragana = { viewModel.openHiragana(openScreen) },
        openLessonKatakana = { viewModel.openKatakana(openScreen) },
        openDailyWaterIntake = { viewModel.openDailyWaterIntake(openScreen) },
        openStepCounter = { viewModel.openStepCounter(openScreen) },
        openNutriGo = { viewModel.openNutriGo(openScreen) },
        openActivityLog = { viewModel.openActivityLog(openScreen) },
        onLogoutClick = { viewModel.onLogoutClick(clearAndNavigate) }
    )
}

@Composable
fun DashboardScreenContent(
    fullName: String = "User",
    openLessonHiragana: () -> Unit,
    openLessonKatakana: () -> Unit,
    openDailyWaterIntake: () -> Unit,
    openStepCounter: () -> Unit,
    openNutriGo: () -> Unit,
    openActivityLog: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
                )
            )
            .padding(0.dp, 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top bar: Profile icons & Logout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                painter = painterResource(id = R.drawable.account),
                contentDescription = "Profile",
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = "Logout",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onLogoutClick() },
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Greeting
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Hello, $fullName",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        // Lesson Progress Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Lesson Progress", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal)
                    Spacer(modifier = Modifier.width(12.dp))

                    LinearProgressIndicator(
                        progress = 0.16f,
                        modifier = Modifier
                            .weight(1f)
                            .height(16.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.LightGray
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable { /* Handle Continue click if needed */ }
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Continue",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = "Right Arrow",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        // Lessons Section
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lessons",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "8 items", fontSize = 14.sp, color = Color(0xFF666666))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LessonCard(
                    titleKanji     = "ひらがな",
                    subtitle       = "Hiragana",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF9B1C1C),
                    onClick         = openLessonHiragana
                )
                LessonCard(
                    titleKanji     = "カタカナ",
                    subtitle       = "Katakana",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF9B1C1C),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "こんにちは",
                    subtitle       = "Hello",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF1E3170),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "かぞく",
                    subtitle       = "Family",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF1E3170),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "たべもの",
                    subtitle       = "Food",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF093D16),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "どこ",
                    subtitle       = "Where",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF093D16),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "いえ",
                    subtitle       = "House",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFFB7520F),
                    onClick         = openLessonHiragana //tobe change
                )
                LessonCard(
                    titleKanji     = "せいかつ",
                    subtitle       = "Lifestyle",
                    width          = 140.dp,
                    height         = 180.dp,
                    backgroundColor = Color(0xFF852E68),
                    onClick         = openLessonHiragana //tobe change
                )
                // Additional LessonCard items if needed
            }
        }

        // Memory Hints Section
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Memory Hints",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "3 items", fontSize = 14.sp, color = Color(0xFF666666))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MemoryHintCard(
                    titleKanji     = "ひらがな",
                    subtitle       = "Hiragana",
                    size           = 140.dp,
                    backgroundColor = Color(0xFFFFD3D3)
                )
                MemoryHintCard(
                    titleKanji     = "カタカナ",
                    subtitle       = "Katakana",
                    size           = 140.dp,
                    backgroundColor = Color(0xFFFFF59D)
                )
                MemoryHintCard(
                    titleKanji     = "かんじ",
                    subtitle       = "Kanji",
                    size           = 140.dp,
                    backgroundColor = Color(0xFFC8F7C5)
                )
            }

        }

        Spacer(Modifier.height(50.dp))

        IconButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .padding(15.dp)
                .size(56.dp)
                .align(Alignment.CenterHorizontally)
        ) {
        }

    }
}

@Composable
fun LessonCard(
    titleKanji: String,
    subtitle: String,
    width: Dp,
    height: Dp,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width, height)
            .clip(RoundedCornerShape(24.dp))
            .shadow(6.dp, RoundedCornerShape(24.dp), clip = false)
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleKanji,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Composable
fun MemoryHintCard(
    titleKanji: String,
    subtitle: String,
    size: Dp,
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(8.dp))
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleKanji,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}


@Composable
fun OptionIcon(
    @DrawableRes iconRes: Int,
    contentDescription: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(56.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp),
            tint = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TangoGOPreview() {
    TangoGOTheme {
        DashboardScreenContent(
            fullName = "Nurin!",
            openLessonHiragana = { },
            openLessonKatakana = { },
            openActivityLog = { },
            openDailyWaterIntake = { },
            openNutriGo = { },
            openStepCounter = { },
            onLogoutClick = { }
        )
    }
}