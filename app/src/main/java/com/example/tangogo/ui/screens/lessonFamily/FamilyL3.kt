package com.example.tangogo.ui.screens.lessonFamily

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonHello.QuestionImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyL3Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isAudioPlaying by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.apply { if (isPlaying) stop(); release() }
            mediaPlayer = null
            isAudioPlaying = false
        }
    }

    val toggleAudio: (Int) -> Unit = { resId ->
        if (isAudioPlaying) {                     // 🔇 stop
            mediaPlayer?.apply { if (isPlaying) stop(); release() }
            mediaPlayer = null
            isAudioPlaying = false
        } else {                                  // 🔊 play
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, resId).apply {
                setOnCompletionListener {
                    it.release()
                    mediaPlayer = null
                    isAudioPlaying = false
                }
                start()
            }
            isAudioPlaying = true
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
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "かぞく",
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
                Text("Lesson 4", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("かぞく", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

                Text("1.かいわと ぶんぽう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Justify)
                Text("Conversation and Grammar", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            DialogCard(
                jpLine1 = "わたしの　かぞくは　３にんです。",
                romaji1 = "Watashi no kazoku wa san-nin desu.",
                jpLine2 = "ちちと　ははと　わたしです。",
                romaji2 = "Chichi to haha to watashi desu.",
                jpLine3 = "わたしたちは　おおさかに　すんでいます。",
                romaji3 = "Watashitachi wa Osaka ni sunde imasu.",
                engLine1 = "There are three people in my family.",
                engLine2 = "It’s my father, my mother, and me.",
                engLine3 = "We live in Osaka.",
                isAudioPlaying = isAudioPlaying,
                onToggleAudio  = { toggleAudio(R.raw.kaiwa037) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("つかわれている ぶんぽう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Grammar used.", fontSize = 18.sp, color = Color.DarkGray)
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
                    Text("_____と______", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____to______", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 1.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("_____は_____に すんでいます。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa_____ni sunde imasu.  ", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("どこ に すんでいますか。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("Doko ni sunde imasu ka.  ", fontSize = 16.sp, color = Color(0xFF757575))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("１–４ の　かぞくの　しゃしんを　みて　ことばを　えらびましょう。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Look at the photos 1–4 and choose the correct words.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            QuestionImageCard(
                question = "_____と わたしです。  ",
                romaji = "_____to watashi desu. ",
                options = listOf("a. つま", "b. おっと","c. こども","d. あに","e. あね"),
                correctAnswer = "a. つま",
                imageResId = R.drawable.fl3q1
            )

            QuestionImageCard(
                question = "_____と わたしと こども ふたりです。",
                romaji = "_____to watashi to kodomo futari desu.",
                options = listOf("a. つま", "b. おっと","c. こども","d. あに","e. あね"),
                correctAnswer = "b. おっと",
                imageResId = R.drawable.fl3q2
            )

            QuestionImageCard(
                question = "ちちと ははと_____と わたしと おとうとです。",
                romaji = "Chichi to haha to_____to watashi to otooto desu.",
                options = listOf("a. つま", "b. おっと","c. こども","d. あに","e. あね"),
                correctAnswer = "e. あね",
                imageResId = R.drawable.fl3q3
            )

            QuestionImageCard(
                question = "ちちと ははと つまと_____ふたりと わたしです。",
                romaji = "Chichi to haha to tsuma to_____futari to watashi desu.",
                options = listOf("a. つま", "b. おっと","c. こども","d. あに","e. あね"),
                correctAnswer = "c. こども",
                imageResId = R.drawable.fl3q4
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("ききましょう。 1 – 5 の かぞくは どこに すんでいますか。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Listen. Where do families 1–5 live?", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            ListenAnswerCard(
                question = "1",
                options = listOf("a. おおさか (Oosaka)", "b. とうきょう (Tokyoo)", "c. ほっかいどう (Hokkaidoo)","d. ひろしま (Hiroshima)","e. おきなわ (Okinawa)"),
                correctAnswer = "a. おおさか (Oosaka)",
                soundResId = R.raw.kaiwa039_1
            )

            ListenAnswerCard(
                question = "2",
                options = listOf("a. おおさか (Oosaka)", "b. とうきょう (Tokyoo)", "c. ほっかいどう (Hokkaidoo)","d. ひろしま (Hiroshima)","e. おきなわ (Okinawa)"),                correctAnswer = "b. とうきょう (Tokyoo)",
                soundResId = R.raw.kaiwa039_2
            )

            ListenAnswerCard(
                question = "3",
                options = listOf("a. おおさか (Oosaka)", "b. とうきょう (Tokyoo)", "c. ほっかいどう (Hokkaidoo)","d. ひろしま (Hiroshima)","e. おきなわ (Okinawa)"),
                correctAnswer = "d. ひろしま (Hiroshima)",
                soundResId = R.raw.kaiwa039_3
            )

            ListenAnswerCard(
                question = "4",
                options = listOf("a. おおさか (Oosaka)", "b. とうきょう (Tokyoo)", "c. ほっかいどう (Hokkaidoo)","d. ひろしま (Hiroshima)","e. おきなわ (Okinawa)"),
                correctAnswer = "e. おきなわ (Okinawa)",
                soundResId = R.raw.kaiwa039_4
            )

            ListenAnswerCard(
                question = "5",
                options = listOf("a. おおさか (Oosaka)", "b. とうきょう (Tokyoo)", "c. ほっかいどう (Hokkaidoo)","d. ひろしま (Hiroshima)","e. おきなわ (Okinawa)"),
                correctAnswer = "c. ほっかいどう (Hokkaidoo)",
                soundResId = R.raw.kaiwa039_5
            )

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
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun DialogCard(
    jpLine1: String,
    romaji1: String,
    jpLine2: String,
    romaji2: String,
    jpLine3: String,
    romaji3: String,
    engLine1: String,
    engLine2: String,
    engLine3: String,
    onToggleAudio: () -> Unit,
    isAudioPlaying: Boolean
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
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = if (isAudioPlaying)
                        Icons.AutoMirrored.Filled.VolumeUp    // shows “playing”
                    else
                        Icons.AutoMirrored.Filled.VolumeOff,  // shows “muted”
                    contentDescription = if (isAudioPlaying) "Mute" else "Play",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onToggleAudio() }
                )
            }

            Text(jpLine1, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji1, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine2, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji2, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine3, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji3, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.LightGray)

            Text(engLine1, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine2, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine3, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenAnswerCard(
    question: String,
    options: List<String>,
    correctAnswer: String,
    soundResId: Int,
) {
    val context = LocalContext.current
    var selectedAnswer by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val isPreview = LocalInspectionMode.current
    val mediaPlayer = remember {
        if (!isPreview) MediaPlayer.create(context, soundResId) else null
    }

    fun playFeedbackSound(correct: Boolean) {
        val soundRes = if (correct) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer?.release()
        }
    }

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

                IconButton(onClick = {
                    mediaPlayer?.let {
                        if (it.isPlaying) {
                            it.pause()
                            it.seekTo(0)
                        }
                        it.start()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Play Sound",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


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
fun FamilyL3ScreenPreview() {
    FamilyL3Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}