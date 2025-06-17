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

data class VocabCard(
    val kana: String,
    val romaji: String,
    val audioRes: Int,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HiraganaSpeakScreen(
    navigateBack: () -> Unit,
    //navigateToNext: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
) {
    val context = LocalContext.current
    val isInPreview = LocalInspectionMode.current

    val vocabList = listOf(
        VocabCard("こんにちは", "konnichiwa", R.raw.konnichiwa, R.drawable.konnichiwa_img),
        VocabCard("ありがとう", "arigatou",  R.raw.arigatou,   R.drawable.arigatou_img),
        VocabCard("おはよう", "ohayou",     R.raw.ohayou,    R.drawable.ohayougozaimasu_img)
    )

    var currentIndex by remember { mutableIntStateOf(0) }
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

    // Auto next logic
    LaunchedEffect(isCorrect) {
        if (isCorrect) {
            delay(2000)
            if (currentIndex < vocabList.lastIndex) {
                currentIndex++
                spokenText = ""
                isCorrect = false
            } else {
                navigateToLessonComplete()
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
                        text = "Quiz",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "きいて はなして",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "Listen and speak.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 2.dp, bottom = 8.dp),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                }

                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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

                        Text(currentCard.kana,   fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        Text(currentCard.romaji, fontSize = 16.sp, color = Color.Gray)

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
                                            isCorrect = result.contains(currentCard.kana)
                                            if (isCorrect) dingPlayer?.start() else errorPlayer?.start()
                                            isListening = false
                                        },
                                        onEnd = { isListening = false }
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isListening) Color(0xFFE6DEF7) else Color(
                                    0xFF061428
                                )
                            ),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier.fillMaxWidth().height(50.dp)
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
                                    (fadeIn(tween(300)) + scaleIn()).togetherWith(fadeOut(tween(300)) + scaleOut())
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
            // Show nothing can show a final image/message later if needed)
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
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja-JP")
    }

    if (SpeechRecognizer.isRecognitionAvailable(context)) {
        val recognizer = SpeechRecognizer.createSpeechRecognizer(context)

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle) {
                val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                onResult(matches?.firstOrNull() ?: "")
                onEnd()
            }

            override fun onError(error: Int) {
                Log.e("SpeechRecognizer", "Error: $error")
                onResult("")
                onEnd()
            }

            override fun onEndOfSpeech() = onEnd()
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        recognizer.startListening(intent)
    } else {
        Log.e("SpeechRecognizer", "Speech recognition not available.")
    }
}

@Preview(showBackground = true)
@Composable
fun HiraganaSpeakScreenPreview() {
    HiraganaSpeakScreen(
        navigateBack = {},
        //navigateToNext = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}
