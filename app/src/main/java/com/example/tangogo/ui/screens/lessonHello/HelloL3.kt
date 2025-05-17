package com.example.tangogo.ui.screens.lessonHello

import android.content.Context
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloL3Screen(
    navigateBack: () -> Unit = {},
    navigateToNext: () -> Unit = {}
) {
    val context = LocalContext.current

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

            // Lesson Heading
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("だい3か", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("どうぞ よろしく", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Text("Douzo yoroshiku", fontSize = 18.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(20.dp))

                Text("2.かいわと ぶんぽう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Conversation and Grammar", fontSize = 18.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dialog Card with sound icon
            DialogCard(
                "キム: はじめまして。きむです。\n          どうぞよろしく。",
                "Kimu: Hajimemashite. Kimu desu.\n            Doozo yoroshiku.",
                "カーラ: カーラです。はじめまして。\n              どうぞよろしく。",
                "Kaara: Kaara desu. Hajimemashite.\n             Doozo yoroshiku.",
                "キム: カーラさん、おしごとはなんです\n          か？",
                "Kimu: Kaara-san, oshigoto wa nandesuka?",
                "カーラ: わたしは がくせいです。\n             あのう、きむさん は せんせい\n             ですか？",
                "Kaara: Watashi wa gakusei desu.\n             Anoo, Kimu-san wa sensei desu ka.",
                "カーラ: いいえ、せんせいじゃないです。\n             がくせいです。",
                "Kimu: Iie, sensei janai desu.Gakusei desu.",

                "Kimu: Nice to meet you. I'm Kimu.\n            Pleasure to meet you.",
                "Kaara: I'm Kaara. Nice to meet you.\n            Pleasure to meet you.",
                "Kimu: Kaara-san, what is your job?",
                "Kaara: I am a student. By the way, is\n            Kimu-san a teacher?",
                "Kimu: No, I am not a teacher. I am a\n            student.",
                context
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
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("_____は______ です。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ desu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("_____は______ じゃない です。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ janai desu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("_____は______ です か。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ desu ka.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 1.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("_____は なん です か。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa nan desu ka.", fontSize = 16.sp, color = Color(0xFF757575))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("えらびましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Choose the correct answer based on the conversation.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            QnACard(
                question = "キムさんは、がくせいですか？",
                romaji = "Kimu-san wa gakusei desu ka?",
                options = listOf("はい、がくせいです", "いいえ、せんせいです"),
                correctAnswer = "はい、がくせいです"
            )

            QnACard(
                question = "カーラさんは、せんせいですか？",
                romaji = "Kaara-san wa sensei desu ka?",
                options = listOf("はい、がくせいです", "いいえ、せんせいです"),
                correctAnswer = "はい、がくせいです"
            )

            Spacer(modifier = Modifier.height(32.dp))

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
fun DialogCard(
    sentence1: String, romaji1: String,
    sentence2: String, romaji2: String,
    sentence3: String, romaji3: String,
    sentence4: String, romaji4: String,
    sentence5: String, romaji5: String,
    translation1: String, translation2: String,
    translation3: String, translation4: String, translation5: String, context: Context
) {
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
                        .clickable { playAudio(context, R.raw.kaiwa017 ) }
                )
            }

            // Dialogue in Japanese with Romaji
            Text(sentence1, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji1, fontSize = 16.sp, color = Color(0xFF757575))

            Spacer(modifier = Modifier.height(10.dp))

            Text(sentence2, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji2, fontSize = 16.sp, color = Color(0xFF757575))

            Spacer(modifier = Modifier.height(10.dp))

            Text(sentence3, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji3, fontSize = 16.sp, color = Color(0xFF757575))

            Spacer(modifier = Modifier.height(10.dp))

            Text(sentence4, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji4, fontSize = 16.sp, color = Color(0xFF757575))

            Spacer(modifier = Modifier.height(10.dp))

            Text(sentence5, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji5, fontSize = 16.sp, color = Color(0xFF757575))

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.LightGray)

            // Translations
            Text(translation1, fontSize = 16.sp, color = Color(0xFF3F3F3F))
            Text(translation2, fontSize = 16.sp, color = Color(0xFF3F3F3F))
            Text(translation3, fontSize = 16.sp, color = Color(0xFF3F3F3F))
            Text(translation4, fontSize = 16.sp, color = Color(0xFF3F3F3F))
            Text(translation5, fontSize = 16.sp, color = Color(0xFF3F3F3F))

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QnACard(
    question: String,
    romaji: String,
    options: List<String>,
    correctAnswer: String
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
                true -> Color(0xFFE0FAE9) // correct - light green
                false -> Color(0xFFFFEBEE) // wrong - light red
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
            Text(question, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(romaji, fontSize = 16.sp, color = Color(0xFF757575))

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
                    options.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                selectedAnswer = label
                                val correct = label == correctAnswer
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
fun HelloL3ScreenPreview() {
    HelloL3Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
