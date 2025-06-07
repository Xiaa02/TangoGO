@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.dashboard

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

// Accompanist Pager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import kotlinx.coroutines.delay

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    clearAndNavigate: (String) -> Unit,
    openScreen: (String) -> Unit,
) {
    val user = viewModel.currentUser.collectAsStateWithLifecycle(User())
    val fullName = "${user.value.firstName} ${user.value.lastName}"

    DashboardScreenContent(
        fullName = fullName,
        openLessonHiragana = { viewModel.openHiragana(openScreen) },
        openLessonKatakana = { viewModel.openKatakana(openScreen) },
        openLessonHello = { viewModel.openHello(openScreen) },
        openLessonFamily = { viewModel.openFamily(openScreen) },
        openLessonFood = { viewModel.openFood(openScreen) },
        openLessonWhere = { viewModel.openWhere(openScreen) },
        openLessonHome = { viewModel.openHome(openScreen) },
        openLessonDaily = { viewModel.openDaily(openScreen) },
        openHiraganaChart  = { viewModel.openHiraganaChart(openScreen) },
        openKatakanaChart  = { viewModel.openKatakanaChart(openScreen) },
        openKanjiChart     = { viewModel.openKanjiChart(openScreen) },
        openSettings      = { viewModel.openSettings(openScreen) },
        onLogoutClick      = { viewModel.onLogoutClick(clearAndNavigate) }
    )
}

@Composable
fun DashboardScreenContent(
    fullName: String = "User",
    openLessonHiragana: () -> Unit,
    openLessonKatakana: () -> Unit,
    openLessonHello: () -> Unit,
    openLessonFamily: () -> Unit,
    openLessonFood: () -> Unit,
    openLessonWhere: () -> Unit,
    openLessonHome: () -> Unit,
    openLessonDaily: () -> Unit,
    openHiraganaChart:  () -> Unit,
    openKatakanaChart:  () -> Unit,
    openKanjiChart:     () -> Unit,
    openSettings:     () -> Unit,
    onLogoutClick:      () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF9F9F9), Color(0xFFF9F9F9))
                )
            )
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment   = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { openSettings() },
                tint = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = "Logout",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { onLogoutClick() },
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Greeting
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Hello, $fullName!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val vocabList = listOf(
            Vocab("食べ物", "たべもの", "Food"),
            Vocab("水", "みず", "Water"),
            Vocab("本", "ほん", "Book"),
            Vocab("家", "いえ", "House"),
            Vocab("車", "くるま", "Car"),
            Vocab("犬", "いぬ", "Dog"),
            Vocab("猫", "ねこ", "Cat"),
            Vocab("学校", "がっこう", "School"),
            Vocab("先生", "せんせい", "Teacher"),
            Vocab("友達", "ともだち", "Friend"),
            Vocab("空", "そら", "Sky"),
            Vocab("雨", "あめ", "Rain"),
            Vocab("山", "やま", "Mountain"),
            Vocab("川", "かわ", "River"),
            Vocab("魚", "さかな", "Fish"),
            Vocab("朝", "あさ", "Morning"),
            Vocab("夜", "よる", "Night"),
            Vocab("火", "ひ", "Fire"),
            Vocab("月", "つき", "Moon"),
            Vocab("花", "はな", "Flower"),
            Vocab("人", "ひと", "Person"),
            Vocab("名前", "なまえ", "Name"),
            Vocab("国", "くに", "Country"),
            Vocab("日本", "にほん", "Japan"),
            Vocab("英語", "えいご", "English (language)"),
            Vocab("お茶", "おちゃ", "Green Tea"),
            Vocab("牛乳", "ぎゅうにゅう", "Milk"),
            Vocab("ご飯", "ごはん", "Cooked Rice / Meal"),
            Vocab("電話", "でんわ", "Telephone"),
            Vocab("時計", "とけい", "Clock / Watch")
        )

        AutoSlidingVocabularyCarousel(
            items = vocabList,
            slideDurationMs = 4000L
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Lessons Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment   = Alignment.CenterVertically
            ) {
                Text("Lessons", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("8 items", fontSize = 14.sp, color = Color(0xFF666666))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LessonCard("ひらがな", "Hiragana",   140.dp, 180.dp, Color(0xFF9B1C1C), openLessonHiragana)
                LessonCard("カタカナ", "Katakana",   140.dp, 180.dp, Color(0xFF9B1C1C), openLessonKatakana)
                LessonCard("こんにちは","Hello",    140.dp, 180.dp, Color(0xFF1E3170), openLessonHello)
                LessonCard("かぞく",   "Family",    140.dp, 180.dp, Color(0xFF1E3170), openLessonFamily)
                LessonCard("たべもの","Food",      140.dp, 180.dp, Color(0xFF093D16), openLessonFood)
                LessonCard("どこ",     "Where",     140.dp, 180.dp, Color(0xFF093D16), openLessonWhere)
                LessonCard("いえ",     "Home",      140.dp, 180.dp, Color(0xFFFF9800), openLessonHome)
                LessonCard("せいかつ","DailyLife",140.dp, 180.dp, Color(0xFFA83760), openLessonDaily)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        //Memory Hints
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment   = Alignment.CenterVertically
            ) {
                Text("Memory Hints", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("3 items", fontSize = 14.sp, color = Color(0xFF666666))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MemoryHintCard("ひらがな", "Hiragana",   140.dp, Color(0xFFFFD3D3), onClick = openHiraganaChart)
                MemoryHintCard("カタカナ", "Katakana",   140.dp, Color(0xFFFFF59D), onClick = openKatakanaChart)
                MemoryHintCard("かんじ",   "Kanji",      140.dp, Color(0xFFC8F7C5), onClick = openKanjiChart)
            }
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
            Text(titleKanji, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(subtitle, fontSize = 14.sp, color = Color.White)
        }
    }
}

@Composable
fun MemoryHintCard(
    titleKanji: String,
    subtitle: String,
    size: Dp,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(titleKanji, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(subtitle, fontSize = 14.sp)
        }
    }
}

@Composable
fun VocabularyWordCard(
    wordKanji: String,
    wordKana: String,
    meaning: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Word of the Day", fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(wordKanji, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(
                    0xFF061428
                )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(wordKana, fontSize = 16.sp, color = Color(0xFF666666))
                    Text(meaning, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
            }
        }
    }
}

// data model for carousel
data class Vocab(val kanji: String, val kana: String, val meaning: String)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingVocabularyCarousel(
    items: List<Vocab>,
    modifier: Modifier = Modifier,
    slideDurationMs: Long = 15000L
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        while (true) {
            delay(slideDurationMs)
            val next = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(next)
        }
    }

    HorizontalPager(
        count = items.size,
        state = pagerState,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        val item = items[page]
        VocabularyWordCard(
            wordKanji = item.kanji,
            wordKana  = item.kana,
            meaning   = item.meaning,
            modifier  = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TangoGOPreview() {
    TangoGOTheme {
        DashboardScreenContent(
            fullName = "Nurin",
            openLessonHiragana = {},
            openLessonKatakana = {},
            openLessonHello = {},
            openLessonFamily = {},
            openLessonFood = {},
            openLessonWhere = {},
            openLessonHome = {},
            openLessonDaily = {},
            openHiraganaChart   = {},
            openKatakanaChart   = {},
            openKanjiChart      = {},
            openSettings      = {},
            onLogoutClick       = {}
        )
    }
}
