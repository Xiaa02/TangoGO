package com.example.tangogo.ui.screens.lessonWhere

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonFood.FoodTextCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereL1Screen(
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
                            text = "どこ",
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
                    "Lesson 6",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "どこで たべますか",
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
                    "たべもの",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Food", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Food list
            FoodTextCard(R.drawable.sushi, "すし", "sushi", "sushi", R.raw.sushi, playAudio)
            FoodTextCard(R.drawable.soba, "そば", "soba", "soba", R.raw.soba, playAudio)
            FoodTextCard(R.drawable.udon, "うどん", "udon", "udon", R.raw.udon, playAudio)
            FoodTextCard(R.drawable.hamburger, "ハンバーガー", "hanbaagaa", "hamburger", R.raw.hanbaagaa, playAudio)
            FoodTextCard(R.drawable.sandwich, "サンドイッチ", "sandoicchi", "sandwich", R.raw.sandoicchi, playAudio)
            FoodTextCard(R.drawable.curry, "カレー", "karee", "curry", R.raw.karee, playAudio)
            FoodTextCard(R.drawable.pizza, "ピザ", "piza", "pizza", R.raw.piza, playAudio)
            FoodTextCard(R.drawable.ramen, "ラーメン", "raamen", "ramen", R.raw.raamen, playAudio)

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

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "a.すし",
                imageResId = R.drawable.sushi
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "b.そば",
                imageResId = R.drawable.soba
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "c.うどん",
                imageResId = R.drawable.udon
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "d.ハンバーガー",
                imageResId = R.drawable.hamburger
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "e.サンドイッチ",
                imageResId = R.drawable.sandwich
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "f.カレー",
                imageResId = R.drawable.curry
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "g.ピザ",
                imageResId = R.drawable.pizza
            )

            QuestionImage2Card(
                options = listOf("a.すし", "b.そば", "c.うどん", "d.ハンバーガー", "e.サンドイッチ", "f.カレー", "g.ピザ", "h.ラーメン"),
                correctAnswer = "h.ラーメン",
                imageResId = R.drawable.ramen
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
fun QuestionImage2Card(
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

@Preview(showBackground = true)
@Composable
fun WhereL1ScreenPreview() {
    WhereL1Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}