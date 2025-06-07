package com.example.tangogo.ui.screens.lessonFamily

import android.media.MediaPlayer
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyL4Screen(
    navigateBack: () -> Unit = {},
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
) {

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
                    "Lesson 4",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "かぞく",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "2.かいわと ぶんぽう",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Justify
                )
                Text(
                    "Conversation and Grammar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dialog Card with conversation
            DialogCard2(
                jpLine1 = "キム：この　おとこのこは　だれですか。",
                romaji1 = "Kimu: Kono otoko-no-ko wa dare desu ka?",
                jpLine2 = "すずき：あにの　こどもです。",
                romaji2 = "Suzuki: Ani no kodomo desu.",
                jpLine3 = "キム：かわいいですね。　なんさいですか。",
                romaji3 = "Kimu: Kawaii desu ne. Nan-sai desu ka?",
                jpLine4 = "すずき：４さいです。",
                romaji4 = "Suzuki: Yon-sai desu.",
                engLine1 = "Kimu: Who is this boy?",
                engLine2 = "Suzuki: He’s my older brother’s child.",
                engLine3 = "Kimu: He’s cute. How old is he?",
                engLine4 = "Suzuki: He’s four years old."
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
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        "____　の　____  ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F3F3F)
                    )
                    Text("____　no　____  ", fontSize = 16.sp, color = Color(0xFF757575))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("なんさいですか（おいくつですか）。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("How old is he/she?", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            ListenAnswer2Card(
                question     = "1. このこは　だれですか。 なんさいですか？",
                options      = listOf(
                    "a. あにの　こどもです。４さいです。",
                    "b. あにです。３３さいです。",
                    "c. ははです。５７さいです。",
                    "d. あねの　こどもです。２さいです。",
                    "e. あねです。２９さいです。"
                ),
                correctAnswer = "a. あにの　こどもです。４さいです。",
                soundResId    = R.raw.kaiwa045_1
            )

            ListenAnswer2Card(
                question     = "2. このこは　だれですか。 なんさいですか？",
                options      = listOf(
                    "a. あにの　こどもです。４さいです。",
                    "b. あにです。３３さいです。",
                    "c. ははです。５７さいです。",
                    "d. あねの　こどもです。２さいです。",
                    "e. あねです。２９さいです。"
                ),
                correctAnswer = "b. あにです。３３さいです。",
                soundResId    = R.raw.kaiwa045_2
            )

            ListenAnswer2Card(
                question     = "3. このこは　だれですか。 なんさいですか？",
                options      = listOf(
                    "a. あにの　こどもです。４さいです。",
                    "b. あにです。３３さいです。",
                    "c. ははです。５７さいです。",
                    "d. あねの　こどもです。２さいです。",
                    "e. あねです。２９さいです。"
                ),
                correctAnswer = "c. ははです。５７さいです。",
                soundResId    = R.raw.kaiwa045_3
            )

            ListenAnswer2Card(
                question     = "4. このこは　だれですか。 なんさいですか？",
                options      = listOf(
                    "a. あにの　こどもです。４さいです。",
                    "b. あにです。３３さいです。",
                    "c. ははです。５７さいです。",
                    "d. あねの　こどもです。２さいです。",
                    "e. あねです。２９さいです。"
                ),
                correctAnswer = "d. あねの　こどもです。２さいです。",
                soundResId    = R.raw.kaiwa045_4
            )

            ListenAnswer2Card(
                question     = "5. このこは　だれですか。 なんさいですか？",
                options      = listOf(
                    "a. あにの　こどもです。４さいです。",
                    "b. あにです。３３さいです。",
                    "c. ははです。５７さいです。",
                    "d. あねの　こどもです。２さいです。",
                    "e. あねです。２９さいです。"
                ),
                correctAnswer = "e. あねです。２９さいです。",
                soundResId    = R.raw.kaiwa045_5
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

@Composable
fun DialogCard2(
    jpLine1: String,
    romaji1: String,
    jpLine2: String,
    romaji2: String,
    jpLine3: String,
    romaji3: String,
    jpLine4: String,
    romaji4: String,
    engLine1: String,
    engLine2: String,
    engLine3: String,
    engLine4: String,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { playAudio(context, R.raw.kaiwa044) }
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

            Text(jpLine4, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji4, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.LightGray)

            Text(engLine1, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine2, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine3, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine4, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenAnswer2Card(
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
    DisposableEffect(mediaPlayer) {
        onDispose { mediaPlayer?.release() }
    }

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
                true  -> Color(0xFFE0FAE9)
                false -> Color(0xFFFFEBEE)
                null  -> Color(0xFFFDFCFB)
            }
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
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
                        contentDescription = "Play question",
                        tint = Color.Black
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // (rest of your dropdown + feedback UI unchanged)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedAnswer,
                    onValueChange = {},
                    label = { Text("Select answer") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
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

            Spacer(Modifier.height(12.dp))
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
fun FamilyL4ScreenPreview() {
    FamilyL4Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}

