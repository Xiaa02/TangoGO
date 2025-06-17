@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonHome

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonWhere.AntonymRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeL3Screen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
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
                            text = "いえ",
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
                    IconButton(onClick = navigateToDashboard) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Dashboard",
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
                    "Lesson 7",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "いい へや ですね",
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
                    "かんじ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Kanji", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAE6FF)),
                //elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    AntonymRow(
                        leftText = "   大きい\n(おおきい)",
                        leftSubText = "ookii \n(Big)",
                        leftAudioResId = R.raw.ooki,
                        rightText = "   小さい\n(ちいさい)",
                        rightSubText = "chiisai \n(Small)",
                        rightAudioResId = R.raw.chiisai,
                        playAudio = playAudio
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                    AntonymRow(
                        leftText = "     新しい\n(あたらしい)",
                        leftSubText = "atarashii \n(New)",
                        leftAudioResId = R.raw.atarashii,
                        rightText = "   古い\n(ふるい)",
                        rightSubText = "furui \n(Old)",
                        rightAudioResId = R.raw.furui,
                        playAudio = playAudio
                    )

                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "かんたんなぶん",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "Basic sentences",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                val kanjiHighlight = SpanStyle(background = Color(0xFFFFF59D))

                // Sentence 1
                Text(
                    buildAnnotatedString {
                        append("1. たなかさんの　いえは　")
                        withStyle(kanjiHighlight) {
                            append("大きい")
                        }
                        append("です。")
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Tanaka-san no ie wa ookii desu.", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(5.dp))
                Text("Tanaka-san’s house is big.", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(10.dp))

                // Sentence 2
                Text(
                    buildAnnotatedString {
                        append("2. わたしの　いえは　")
                        withStyle(kanjiHighlight) {
                            append("小さい")
                        }
                        append("です。")
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Watashi no ie wa chiisai desu.", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(5.dp))
                Text("My house is small.", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(10.dp))

                // Sentence 3
                Text(
                    buildAnnotatedString {
                        append("3. ともだちの　いえは　")
                        withStyle(kanjiHighlight) {
                            append("新しくない")
                        }
                        append("です。")
                        withStyle(kanjiHighlight) {
                            append("古い")
                        }
                        append("です。")
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Tomodachi no ie wa atarashikunai desu. Furui desu.", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(5.dp))
                Text("My friend’s house is not new. It is old.", fontSize = 16.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "ことばをあわせましょう。",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Match the Japanese word to its English meaning.",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            QuizTickMatch(
                context = context,
                playAudio = { resId -> playAudio(resId) }
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = navigateToLessonComplete,
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
fun QuizTickMatch(
    context: Context = LocalContext.current,
    playAudio: (Int) -> Unit
) {
    val quizItems = listOf(
        Triple("A. 大きい", "ookii", listOf("Big", "Small", "New", "Old")),
        Triple("B. 小さい", "chiisai", listOf("Small", "Big", "Old", "New")),
        Triple("C. 新しい", "atarashii", listOf("Old", "New", "Big", "Small")),
        Triple("D. 古い", "furui", listOf("New", "Small", "Old", "Big"))
    )

    val correctAnswers = mapOf(
        "大きい" to "Big",
        "小さい" to "Small",
        "新しい" to "New",
        "古い" to "Old"
    )

    val audioMap = mapOf(
        "大きい" to R.raw.ooki,
        "小さい" to R.raw.chiisai,
        "新しい" to R.raw.atarashii,
        "古い" to R.raw.furui
    )

    val selections = remember { mutableStateMapOf<String, String?>() }
    val correctnessMap = remember { mutableStateMapOf<String, Boolean?>() }

    fun playFeedbackSound(isCorrect: Boolean) {
        val soundRes = if (isCorrect) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        quizItems.forEach { (label, romaji, options) ->
            val kanji = label.substringAfter(". ").trim()
            if (!selections.containsKey(kanji)) {
                selections[kanji] = null
                correctnessMap[kanji] = null
            }

            val selected = selections[kanji]
            val isCorrect = correctnessMap[kanji]
            val audioResId = audioMap[kanji]

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (isCorrect) {
                        true -> Color(0xFFE0FAE9)
                        false -> Color(0xFFFFEBEE)
                        else -> Color(0xFFFDFCFB)
                    }
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(romaji, fontSize = 16.sp, color = Color.Gray)
                        }
                        if (audioResId != null) {
                            IconButton(onClick = { playAudio(audioResId) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Play Audio",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    options.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    selections[kanji] = option
                                    val correct = correctAnswers[kanji] == option
                                    correctnessMap[kanji] = correct
                                    playFeedbackSound(correct)
                                }
                        ) {
                            Checkbox(
                                checked = selected == option,
                                onCheckedChange = {
                                    selections[kanji] = option
                                    val correct = correctAnswers[kanji] == option
                                    correctnessMap[kanji] = correct
                                    playFeedbackSound(correct)
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(option, fontWeight = FontWeight.Medium)
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))

                    isCorrect?.let {
                        Text(
                            text = if (it) "⭕ Correct!" else "❌ Try again.",
                            color = if (it) Color(0xFF388E3C) else Color(0xFFD32F2F),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeL3ScreenPreview() {
    HomeL3Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}

