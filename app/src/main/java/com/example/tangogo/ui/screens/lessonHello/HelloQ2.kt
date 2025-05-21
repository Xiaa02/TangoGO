@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonHello

import android.media.MediaPlayer
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloQ2Screen(
    navigateBack: () -> Unit = {},
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
) {
    val context = LocalContext.current

    // MediaPlayer for question audios
    var questionMediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    // MediaPlayer for feedback sounds (ding / error)
    var feedbackMediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Function to play question audio, ensuring only one at a time
    val playQuestionAudio: (Int) -> Unit = { resId ->
        questionMediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        questionMediaPlayer = MediaPlayer.create(context, resId).apply {
            setOnCompletionListener {
                it.release()
                questionMediaPlayer = null
            }
            start()
        }
    }

    // Function to play feedback sound, ensuring only one at a time
    val playFeedbackSound: (Boolean) -> Unit = { correct ->
        feedbackMediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        val soundRes = if (correct) R.raw.ding else R.raw.error
        feedbackMediaPlayer = MediaPlayer.create(context, soundRes).apply {
            setOnCompletionListener {
                it.release()
                feedbackMediaPlayer = null
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
                            text = "こんにちは",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Quiz",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "____に　ひらがな（は・も）を　かきましょう。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Justify
                )
                Text(
                    "Write hiragana (wa or mo) in the blanks.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Justify
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ListenSelectCard(
                question = "1\n" +
                        "さとう：ヤンさんは　エンジニアですか。\n" +
                        "ヤン：いいえ。わたし____エンジニアじゃないです。\n" +
                        "さとう：そうですか。",
                romaji = "Satoo: Yan-san wa enjinia desu ka?\n" +
                        "Yan: Iie. Watashi____enjinia janai desu.\n" +
                        "Satoo: Soo desu ka.",
                options = listOf("は", "も"),
                correctAnswer = "は",
                soundResId = R.raw.kaiwa029_1,
                playAudio = playQuestionAudio,
                playFeedbackSound = playFeedbackSound
            )

            ListenSelectCard(
                question = "2\n" +
                        "きむら：おしごとは　なんですか。\n" +
                        "やぎ：わたしは こうむいんです。\n" +
                        "きむら：そうですか。わたし____こうむいんです。",
                romaji = "Kimura: Oshigoto wa nan desu ka?\n" +
                        "Yagi: Watashi wa koomuin desu.\n" +
                        "Kimura: Soo desu ka. Watashi____koomuin desu.",
                options = listOf("は", "も"),
                correctAnswer = "も",
                soundResId = R.raw.kaiwa029_2,
                playAudio = playQuestionAudio,
                playFeedbackSound = playFeedbackSound
            )

            ListenSelectCard(
                question = "3\n" +
                        "カーラ：やぎさんは フランスごが　できますか。\n" +
                        "やぎ：はい、すこし　できます。\n" +
                        "　　　アラビアご____できます。\n" +
                        "カーラ：そうですか。すごいですね。",
                romaji = "Kaara: Yagi-san wa Furansugo ga dekimasu ka?\n" +
                        "Yagi: Hai, sukoshi dekimasu.\n" +
                        "Arabiago____dekimasu.\n" +
                        "Kaara: Soo desu ka. Sugoi desu ne.",
                options = listOf("は", "も"),
                correctAnswer = "も",
                soundResId = R.raw.kaiwa029_3,
                playAudio = playQuestionAudio,
                playFeedbackSound = playFeedbackSound
            )

            ListenSelectCard(
                question = "4\n" +
                        "やぎ：キムさんは なにごが　できますか。\n" +
                        "キム：えいごが　できます。\n" +
                        "やぎ：ちゅうごくごは？\n" +
                        "キム：ちゅうごくご____できません。",
                romaji = "Yagi: Kimu-san wa nanigo ga dekimasu ka?\n" +
                        "Kimu: Eigo ga dekimasu.\n" +
                        "Yagi: Chuugokugo wa?\n" +
                        "Kimu: Chuugokugo____dekimasen.",
                options = listOf("は", "も"),
                correctAnswer = "は",
                soundResId = R.raw.kaiwa029_4,
                playAudio = playQuestionAudio,
                playFeedbackSound = playFeedbackSound
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
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenSelectCard(
    question: String,
    romaji: String,
    options: List<String>,
    correctAnswer: String,
    soundResId: Int,
    playAudio: (Int) -> Unit,
    playFeedbackSound: (Boolean) -> Unit,
) {
    var selectedAnswer by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (isCorrect) {
                true -> Color(0xFFE0FAE9) // correct - light green
                false -> Color(0xFFFFEBEE) // wrong - light red
                else -> Color(0xFFFDFCFB)
            }
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val questionNumber = question.substringBefore('\n')

                Text(
                    text = questionNumber,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )

                IconButton(onClick = { playAudio(soundResId) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Play Sound",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.substringAfter('\n'),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F3F3F)
            )
            Text(
                text = romaji,
                fontSize = 16.sp,
                color = Color(0xFF757575)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedAnswer,
                    onValueChange = {},
                    label = { Text("Select answer") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedAnswer = option
                                val correct = option == correctAnswer
                                isCorrect = correct
                                playFeedbackSound(correct)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

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

@Preview(showBackground = true)
@Composable
fun HelloQ2ScreenPreview() {
    HelloQ2Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}
