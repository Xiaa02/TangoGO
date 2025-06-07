package com.example.tangogo.ui.screens.lessonDailyLife

import android.media.MediaPlayer
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun DailyL1Screen(
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
                    "せいかつ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Daily Life", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Daily life items
            DailyTextCard(R.drawable.wakeup, "おきます", "okimasu", "to get up", R.raw.okimasu, playAudio)
            DailyTextCard(R.drawable.sleep, "ねます", "nemasu", "to go to bed", R.raw.nemasu, playAudio)
            DailyTextCard(R.drawable.study, "べんきょう します", "benkyoo shimasu", "to study", R.raw.benkyooshimasu, playAudio)
            DailyTextCard(R.drawable.work, "しごと します", "shigoto shimasu", "to work", R.raw.shigotoshimasu, playAudio)
            DailyTextCard(R.drawable.exercise, "うんどうを します", "undoo o shimasu", "to exercise", R.raw.undoooshimasu, playAudio)
            DailyTextCard(R.drawable.stroll, "さんぽを　します", "sanpo o shimasu", "to take a walk", R.raw.sanpooshimasu, playAudio)
            DailyTextCard(R.drawable.gotoschool, "がっこうに　いきます", "gakkoo ni ikimasu", "go to school", R.raw.gakkooniikimasu, playAudio)
            DailyTextCard(R.drawable.gotowork, "かいしゃに　いきます", "kaisha ni ikimasu", "go to work", R.raw.kaishaniikimasu, playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "にほんごで なんですか。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "What is it in Japanese?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "a.おきます",
                imageResId = R.drawable.wakeup
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "b.ねます",
                imageResId = R.drawable.sleep
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "d.しごと します",
                imageResId = R.drawable.work
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "c.べんきょう します",
                imageResId = R.drawable.study
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "e.うんどうを します",
                imageResId = R.drawable.exercise
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "f.さんぽを　します",
                imageResId = R.drawable.stroll
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "g.がっこうに　いきます",
                imageResId = R.drawable.gotoschool
            )

            QuestionImage4Card(
                options = listOf("a.おきます", "b.ねます", "c.べんきょう します", "d.しごと します", "e.うんどうを します", "f.さんぽを　します", "g.がっこうに　いきます", "h.かいしゃに　いきます"),
                correctAnswer = "h.かいしゃに　いきます",
                imageResId = R.drawable.gotowork
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionImage4Card(
    options: List<String>,
    correctAnswer: String,
    imageResId: Int? = null
) {
    val context = LocalContext.current
    var selectedAnswer by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var expanded by remember { mutableStateOf(false) }

    fun playFeedbackSound(correct: Boolean) {
        val soundRes = if (correct) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (isCorrect) {
                true -> Color(0xFFE0FAE9)
                false -> Color(0xFFFFEBEE)
                else -> Color(0xFFFDFCFB)
            }
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            imageResId?.let { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Crop
                )
            }

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

@Composable
fun DailyTextCard(
    imageResId: Int,
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
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxWidth()
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Word Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(word, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(reading, fontSize = 16.sp, color = Color(0xFF757575), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(meaning, fontSize = 16.sp, color = Color(0xFF000000), textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DailyL1ScreenPreview() {
    DailyL1Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}