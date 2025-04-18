package com.example.tangogo.ui.screens.lessonHiragana

import android.content.Context
import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiraganaScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit
) {
    val correctAnswer = listOf("あ", "さ")
    val options = listOf("あ", "お", "さ", "き")

    var selectedAnswers by remember { mutableStateOf<List<String>>(emptyList()) }
    var showCorrectPopup by remember { mutableStateOf(false) }
    var showWrongPopup by remember { mutableStateOf(false) }

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
                            text = "ひらがな - Hiragana",
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
                    IconButton(onClick = { navigateToDashboard() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Logout"
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
            Text(
                text = "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Tap the matching pairs",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Play Sound",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    playAudio(context, R.raw.asa_audio)
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
                            painter = painterResource(id = R.drawable.asa_img),
                            contentDescription = "Morning",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("morning", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("asa", fontSize = 16.sp)
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
                        playAudio(context, R.raw.error) // ❌ play sound immediately
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
                    .padding(horizontal = 25.dp)
                    .height(50.dp)
            ) {
                Text("Check", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showCorrectPopup) {
        ModalBottomSheet(
            onDismissRequest = { },
            containerColor = Color.White,
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "✅ Nicely done!",
                    color = Color(0xFF4CAF50),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        showCorrectPopup = false
                        selectedAnswers = emptyList()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("CONTINUE", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }

    if (showWrongPopup) {
        ModalBottomSheet(
            onDismissRequest = { },
            containerColor = Color.White,
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "❌ Incorrect",
                    color = Color(0xFFE57373),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Text("Correct Answer:", color = Color.Black, fontSize = 16.sp)
                Spacer(Modifier.height(4.dp))
                Text("あさ", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("asa", color = Color.Gray, fontSize = 16.sp)
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        showWrongPopup = false
                        selectedAnswers = emptyList()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("GOT IT", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }

    BackHandler(onBack = navigateBack)
}

fun playAudio(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener { it.release() }
    mediaPlayer.start()
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HiraganaScreenPreview() {
    HiraganaScreen(
        navigateBack = {},
        navigateToDashboard = {}
    )
}
