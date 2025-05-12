package com.example.tangogo.ui.screens.lessonHiragana

import android.Manifest
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.tangogo.R
import kotlinx.coroutines.delay


data class VocabCard(val word: String, val audioRes: Int, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HiraganaSpeakScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current
    val isInPreview = LocalInspectionMode.current

    val vocabList = listOf(
        VocabCard("こんにちは", R.raw.konnichiwa, R.drawable.konnichiwa_img),
        VocabCard("ありがとう", R.raw.arigatou, R.drawable.arigatou_img),
        VocabCard("おはよう", R.raw.ohayou, R.drawable.ohayou_img)
    )

    var currentIndex by remember { mutableStateOf(0) }
    var spokenText by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf(false) }
    var isListening by remember { mutableStateOf(false) }
    var permissionGranted by remember { mutableStateOf(false) }

    val currentCard = vocabList.getOrNull(currentIndex)

    val dingPlayer = remember { if (!isInPreview) MediaPlayer.create(context, R.raw.ding) else null }
    val errorPlayer = remember { if (!isInPreview) MediaPlayer.create(context, R.raw.incorrect) else null }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> permissionGranted = granted }

    LaunchedEffect(Unit) {
        if (!isInPreview) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    // Auto next logic (moving to the next card when the answer is correct)
    LaunchedEffect(isCorrect) {
        if (isCorrect) {
            delay(2000)
            // Ensure the index doesn't go out of bounds
            if (currentIndex < vocabList.lastIndex) {
                currentIndex++
                spokenText = ""
                isCorrect = false
            } else {
                // Optionally, you can add a "Lesson Complete" state here
                Log.d("Lesson", "You have completed all vocab cards.")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("ひらがな", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = navigateToDashboard) {
                        Icon(painterResource(id = R.drawable.ic_logout), contentDescription = "Logout")
                    }
                }
            )
        }
    ) { padding ->
        if (currentCard != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "だい1か",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = "ひらがな",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = "Hiragana",
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "きいて はなして",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )

                    Text(
                        text = "Listen and speak.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Play Sound",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        if (!isInPreview) {
                                            val player = MediaPlayer.create(context, currentCard.audioRes)
                                            player.setOnCompletionListener { it.release() }
                                            player.start()
                                        }
                                    }
                            )
                        }

                        Text(currentCard.word, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                        Spacer(modifier = Modifier.height(24.dp))

                        Image(
                            painter = painterResource(id = currentCard.imageRes),
                            contentDescription = "Word Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(220.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (permissionGranted) {
                                    isListening = true
                                    startSpeechToText(
                                        context = context,
                                        onResult = { result ->
                                            spokenText = result
                                            isCorrect = result.contains(currentCard.word)
                                            if (isCorrect) dingPlayer?.start() else errorPlayer?.start()
                                            isListening = false
                                        },
                                        onEnd = { isListening = false }
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isListening) Color(0xFFE6DEF7) else Color(0xFF8583CC)
                            ),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(if (isListening) "Listening..." else "Tap to Speak", fontWeight = FontWeight.Bold)
                        }

                        if (isListening) {
                            Spacer(modifier = Modifier.height(12.dp))
                            CircularProgressIndicator(color = Color(0xFF7C4DFF))
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        if (spokenText.isNotBlank()) {
                            Text("You said: $spokenText", fontSize = 16.sp)

                            AnimatedContent(
                                targetState = isCorrect,
                                transitionSpec = {
                                    fadeIn(tween(300)) + scaleIn() with fadeOut(tween(300)) + scaleOut()
                                }
                            ) { correct ->
                                if (correct) {
                                    Text("⭕", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFF81C784))
                                } else {
                                    Text("❌", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE57373))
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Lesson Complete!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            }
        }
    }
}

fun startSpeechToText(
    context: Context,
    onResult: (String) -> Unit,
    onEnd: () -> Unit
) {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja-JP") // Japanese language for your use case
    }

    // Check if speech recognition is available on the device
    if (SpeechRecognizer.isRecognitionAvailable(context)) {
        val recognizer = SpeechRecognizer.createSpeechRecognizer(context)

        // Log when speech recognition is triggered
        Log.d("SpeechRecognizer", "Starting speech recognition...")

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle) {
                val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null && matches.isNotEmpty()) {
                    Log.d("SpeechRecognizer", "Results: ${matches.first()}")
                    onResult(matches.first())
                } else {
                    Log.d("SpeechRecognizer", "No speech results.")
                    onResult("")
                }
                onEnd()
            }

            override fun onError(error: Int) {
                Log.e("SpeechRecognizer", "Error: $error")
                onResult("") // Reset result
                onEnd() // End listening
            }

            override fun onEndOfSpeech() {
                Log.d("SpeechRecognizer", "End of speech detected")
                onEnd() // End listening
            }

            // Optional: You can handle other methods based on your needs
            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("SpeechRecognizer", "Ready for speech...")
            }

            override fun onBeginningOfSpeech() {
                Log.d("SpeechRecognizer", "Speech started...")
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        recognizer.startListening(intent)
    } else {
        Log.e("SpeechRecognizer", "Speech recognition not available on this device.")
        // Optional: Show a message to the user if recognition is not available
    }
}

@Preview(showBackground = true)
@Composable
fun HiraganaSpeakScreenPreview() {
    HiraganaSpeakScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToNext = {}
    )

}
