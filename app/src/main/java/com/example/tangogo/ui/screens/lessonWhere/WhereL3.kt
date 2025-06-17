package com.example.tangogo.ui.screens.lessonWhere

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.ui.screens.lessonHello.QnACard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereL3Screen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
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
                    "はんたいご",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Antonyms (Adjectives)", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

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
                    AntonymRow(
                        leftText = "おいしいです",
                        leftSubText = "oishii desu \n(Delicious)",
                        leftAudioResId = R.raw.oishiidesu,
                        rightText = "まずいです",
                        rightSubText = "mazui desu \n(Not delicious)",
                        rightAudioResId = R.raw.mazuidesu,
                        playAudio = playAudio
                    )
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                    AntonymRow(
                        leftText = "たかいです",
                        leftSubText = "takai desu \n(Expensive)",
                        leftAudioResId = R.raw.takaidesu,
                        rightText = "やすいです",
                        rightSubText = "yasui desu \n(Cheap)",
                        rightAudioResId = R.raw.yasuidesu,
                        playAudio = playAudio
                    )
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                    AntonymRow(
                        leftText = "はやいです",
                        leftSubText = "hayai desu \n(Fast)",
                        leftAudioResId = R.raw.hayaidesu,
                        rightText = "おそいです",
                        rightSubText = "osoi desu \n(Slow)",
                        rightAudioResId = R.raw.osoidesu,
                        playAudio = playAudio
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "はんたいの いみの ことばを えらびましょう。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "Choose the word with the opposite meaning.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            QnACard(
                question = "まずいです",
                romaji = "mazui desu",
                options = listOf("a.やすいです", "b.おそいです", "c.おいしいです"),
                correctAnswer = "c.おいしいです"
            )

            QnACard(
                question = "たかいです",
                romaji = "takai desu",
                options = listOf("a.やすいです", "b.おそいです", "c.おいしいです"),
                correctAnswer = "a.やすいです"
            )

            QnACard(
                question = "はやいです",
                romaji = "hayai desu",
                options = listOf("a.やすいです", "b.おそいです", "c.おいしいです"),
                correctAnswer = "b.おそいです"
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("かんじ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Kanji", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDAE6FF)),
                    //elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("    食べます　     飲みます", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                        Text("     ta be masu           no mi masu", fontSize = 16.sp, color = Color(0xFF757575))

                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "かんじを えらびましょう。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "Choose the kanji.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            QnACard(
                question = "ラーメンを_____。ピザも_____。",
                romaji = "Rāmen o_____.Piza mo_____.",
                options = listOf("a.食べます", "b. 飲みます"),
                correctAnswer = "a.食べます"
            )

            QnACard(
                question = "コーヒーを_____。",
                romaji = "Kōhī o _____.",
                options = listOf("a.食べます", "b. 飲みます"),
                correctAnswer = "b. 飲みます"
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
fun AntonymRow(
    leftText: String,
    leftSubText: String,
    leftAudioResId: Int,
    rightText: String,
    rightSubText: String,
    rightAudioResId: Int,
    playAudio: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side: Icon on top, text below
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { playAudio(leftAudioResId) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play audio",
                    tint = Color(0xFF061428)
                )
            }
            Text(leftText, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(leftSubText, fontSize = 16.sp, color = Color(0xFF757575))
        }

        Text("↔", fontSize = 28.sp, color = Color.Gray)

        // Right side: Icon on top, text below
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { playAudio(rightAudioResId) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play audio",
                    tint = Color(0xFF061428)
                )
            }
            Text(rightText, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
            Text(rightSubText, fontSize = 16.sp, color = Color(0xFF757575))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhereL3ScreenPreview() {
    WhereL3Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}
