package com.example.tangogo.ui.screens.lessonHiragana

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonKatakana.playAudio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiraganaQ2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val correctAnswer = listOf("よ", "る")
    val options = listOf("き", "よ", "る", "あ")

    var selectedAnswers by remember { mutableStateOf<List<String>>(emptyList()) }
    var showCorrectPopup by remember { mutableStateOf(false) }
    var showWrongPopup by remember { mutableStateOf(false) }

    val correctSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )
    val wrongSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ひらがな",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navigateToNext() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_next),
                            contentDescription = "Next"
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Quiz",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "ひらがなを えらびましょう",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Choose the correct hiragana",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 2.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(300.dp)
                    .height(310.dp)
                    .background(Color(0xFFE6DEF7), RoundedCornerShape(15.dp))
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
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
                                .size(28.dp)
                                .clickable {
                                    playAudio(context, R.raw.yoru)
                                }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(width = 250.dp, height = 160.dp)
                            .background(Color.LightGray)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.yoru_img),
                            contentDescription = "Evening/Night",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("evening/night", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("yoru", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column {
                for (row in options.chunked(2)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        row.forEach { character ->
                            val color = if (selectedAnswers.contains(character)) {
                                Color(0xFFE38AAE)
                            } else {
                                Color(0xFFFFD6DA)
                            }

                            Button(
                                onClick = {
                                    selectedAnswers = selectedAnswers.toMutableList().apply {
                                        if (contains(character)) remove(character)
                                        else add(character)
                                    }
                                    playAudio(
                                        context, when (character) {
                                            "き" -> R.raw.ki
                                            "よ" -> R.raw.yo
                                            "る" -> R.raw.ru
                                            "あ" -> R.raw.a
                                            else -> R.raw.yoru
                                        }
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = color),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(60.dp)
                            ) {
                                Text(character, fontSize = 24.sp, color = Color.Black)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val allCorrect = correctAnswer.all { selectedAnswers.contains(it) }
                    val noWrong = selectedAnswers.all { correctAnswer.contains(it) }

                    if (allCorrect && noWrong) {
                        playAudio(context, R.raw.ding) // ✅ play sound immediately
                        showCorrectPopup = true
                    } else {
                        playAudio(context, R.raw.incorrect) // ❌ play sound immediately
                        showWrongPopup = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8583CC),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(50.dp)
            ) {
                Text("Check", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showCorrectPopup) {
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = correctSheetState,
            containerColor = Color.White,
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            BackHandler(enabled = true) {}
            Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("⭕ Good job!", color = Color(0xFF4CAF50), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Text("Explanation:", fontSize = 16.sp)
                Spacer(Modifier.height(4.dp))
                Text("よる - yoru", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("よ = yo", color = Color.Gray, fontSize = 16.sp)
                Text("る = ru", color = Color.Gray, fontSize = 16.sp)
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        showCorrectPopup = false
                        selectedAnswers = emptyList()
                        navigateToNext()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("CONTINUE", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }

    if (showWrongPopup) {
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = wrongSheetState,
            containerColor = Color.White,
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            BackHandler(enabled = true) {}
            Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("❌ Incorrect", color = Color(0xFFE57373), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Text("Correct Answer:", fontSize = 16.sp)
                Spacer(Modifier.height(4.dp))
                Text("よる", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("yoru", color = Color.Gray, fontSize = 16.sp)
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        showWrongPopup = false
                        selectedAnswers = emptyList()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("GOT IT", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }

    BackHandler(onBack = navigateBack)
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HiraganaQ2ScreenPreview() {
    HiraganaQ2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}