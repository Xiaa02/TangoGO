package com.example.tangogo.ui.screens.lessonDailyLife

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonHello.QnACard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyL2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current

    // Shared MediaPlayer for all audio playback on this screen
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    val playAudio: (Int) -> Unit = { resId ->
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = MediaPlayer.create(context, resId).apply {
            setOnCompletionListener {
                it.release()
                mediaPlayer = null
            }
            start()
        }
    }

    Scaffold(
        containerColor = Color(0xFFF3F0FF),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3F0FF),
                    titleContentColor = Color(0xFF3F3F3F)
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "せいかつ",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color(0xFF3F3F3F)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = navigateToNext) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_next),
                            contentDescription = "Next",
                            tint = Color(0xFF3F3F3F)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Section Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Lesson 8",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "なんじに おきますか",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "どうし",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Verbs – ます-form", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            DailyTextCard("みます", "mimasu", "to see", R.raw.mimasu, playAudio)
            DailyTextCard("よみます", "yomimasu", "to read", R.raw.yomimasu, playAudio)
            DailyTextCard("かきます", "kakimasu", "to write", R.raw.kakimasu, playAudio)
            DailyTextCard("ききます", "kikimasu", "to listen", R.raw.kikimasu, playAudio)
            DailyTextCard("します", "shimasu", "to do", R.raw.shimasu, playAudio)
            DailyTextCard("はいります", "hairimasu", "to enter/to get into", R.raw.hairimasu, playAudio)
            DailyTextCard("かえります", "kaerimasu", "to go home", R.raw.kaerimasu, playAudio)
            DailyTextCard("いきます", "ikimasu", "to go", R.raw.ikimasu, playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "どれですか。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "Which is the correct word?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            QnACard(
                question = "テレビを _____",
                romaji = "terebi o _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "a.みます"
            )

            QnACard(
                question = "おんがくを _____",
                romaji = "ongaku o _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "b.よみます"
            )

            QnACard(
                question = "しんぶんを _____",
                romaji = "shinbun o _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "c.かきます"
            )

            QnACard(
                question = "にっきを _____",
                romaji = "nikki o _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "d.ききます"
            )

            QnACard(
                question = "かじを _____",
                romaji = "kaji o _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "e.します"
            )

            QnACard(
                question = "おふろに _____",
                romaji = "ofuro ni _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "f.はいります"
            )

            QnACard(
                question = "がっこうに _____",
                romaji = "gakkou ni _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "g.かえります"
            )

            QnACard(
                question = "うちに _____",
                romaji = "uchi ni _____",
                options = listOf("a.みます", "b.よみます", "c.かきます", "d.ききます", "e.します", "f.はいります", "g.かえります", "h.いきます"),
                correctAnswer = "h.いきます"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = navigateToNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF061428),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun DailyTextCard(
    word: String,
    reading: String,
    meaning: String,
    soundResId: Int,
    playAudio: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFCFB)),
        //elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { playAudio(soundResId) }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
            ) {
                Text(word, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(2.dp))
                Text(reading, fontSize = 16.sp, color = Color(0xFF757575), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(2.dp))
                Text(meaning, fontSize = 16.sp, color = Color(0xFF000000), textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyL2ScreenPreview() {
    DailyL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}