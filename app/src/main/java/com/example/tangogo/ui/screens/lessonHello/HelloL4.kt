@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonHello

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
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

/* ─────────────────────────────  HELLO LESSON 4  ───────────────────────────── */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloL4Screen(
    navigateBack: () -> Unit = {},
    navigateToNext: () -> Unit = {}
) {
    val context = LocalContext.current

    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isAudioPlaying by remember { mutableStateOf(false) }

    /* ─── Stop & release when user leaves this screen ─── */
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.apply {
                if (isPlaying) stop()
                release()
            }
            mediaPlayer = null
            isAudioPlaying = false
        }
    }

    /* ─── One-icon Play / Mute toggle ─── */
    val toggleAudio: (Int) -> Unit = { resId ->
        if (isAudioPlaying) {                    // ► stop
            mediaPlayer?.apply {
                if (isPlaying) stop()
                release()
            }
            mediaPlayer = null
            isAudioPlaying = false
        } else {                                 // ► play
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

    /* ─────────────────────────  UI  ───────────────────────── */
    Scaffold(
        containerColor = Color(0xFFF3F0FF),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3F0FF),
                    titleContentColor = Color(0xFF3F3F3F)
                ),
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "こんにちは",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
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

            /* ──── Headings ──── */
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("Lesson 4", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("どうぞ よろしく", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

                Spacer(Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(Modifier.height(10.dp))

            /* ──── Dialog ──── */
            DialogCard(
                jpLine1 = "のだ：カーラさん、おくには？",
                romaji1 = "Noda: Kaara-san, okuni wa?",
                jpLine2 = "カーラ：フランスです。のださん は\n                 フランスごが　できますか。",
                romaji2 = "Kaara: Furansu desu. Noda-san wa\n             Furansugo ga dekimasu ka.",
                jpLine3 = "のだ：いいえ、できません。カーラ\n            さん は にほんごが　できます\n             か。",
                romaji3 = "Noda: Iie, dekimasen. Kaara-san wa\n             Nihongo ga dekimasu ka.",
                jpLine4 = "カーラ：はい、すこし　できます。",
                romaji4 = "Kaara: Hai, sukoshi dekimasu.",
                engLine1 = "Noda: Kaara-san, where are you from?",
                engLine2 = "Kaara: I’m from France. Can you speak\n             French?",
                engLine3 = "Noda: No, I can’t. Can you speak Japanese?",
                engLine4 = "Kaara: Yes, I can speak a little.",
                toggleAudio    = toggleAudio,
                isAudioPlaying = isAudioPlaying
            )

            Spacer(Modifier.height(32.dp))

            /* ──── Grammar Card ──── */
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("つかわれている ぶんぽう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Grammar used.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAE6FF)),
                //elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.Start) {
                    Text("_____は______ が できます。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ ga dekimasu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(Modifier.height(12.dp))

                    Text("_____は______ は できません。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ wa dekimasen.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(Modifier.height(12.dp))

                    Text("_____は______ が できますか。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("_____wa______ ga dekimasu ka.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(Modifier.height(16.dp))
                }
            }

            Spacer(Modifier.height(32.dp))

            /* ──── Q&A Section ──── */
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("なに ご が できます か。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Which languages can they speak based on the conversation.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(Modifier.height(10.dp))

            QnACard(
                question = "カーラさん",
                romaji = "Kaara-san",
                options = listOf("フランスご & にほんご", "にほんご"),
                correctAnswer = "フランスご & にほんご"
            )

            QnACard(
                question = "のださん",
                romaji = "Noda-san",
                options = listOf("にほんご", "フランスご & にほんご"),
                correctAnswer = "にほんご"
            )

            Spacer(Modifier.height(32.dp))

            /* ──── Continue Button ──── */
            Button(
                onClick = navigateToNext,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF061428), contentColor = Color.White),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(50))
            ) { Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold) }

            Spacer(Modifier.height(32.dp))
        }
    }
}

/* ──────────────────────────  DIALOG CARD  ────────────────────────── */

@Composable
fun DialogCard(
    jpLine1: String, romaji1: String,
    jpLine2: String, romaji2: String,
    jpLine3: String, romaji3: String,
    jpLine4: String, romaji4: String,
    engLine1: String, engLine2: String,
    engLine3: String, engLine4: String,
    toggleAudio: (Int) -> Unit,
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
        Column(Modifier.padding(16.dp).fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = if (isAudioPlaying)
                        Icons.Filled.VolumeUp
                    else
                        Icons.AutoMirrored.Filled.VolumeOff,
                    contentDescription = if (isAudioPlaying) "Mute" else "Play",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { toggleAudio(R.raw.kaiwa023) }
                )
            }

            Text(jpLine1, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji1, fontSize = 16.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))

            Text(jpLine2, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji2, fontSize = 16.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))

            Text(jpLine3, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji3, fontSize = 16.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))

            Text(jpLine4, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji4, fontSize = 16.sp, color = Color.Gray)

            Divider(Modifier.padding(vertical = 16.dp), color = Color.LightGray)

            Text(engLine1, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(engLine2, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(engLine3, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(engLine4, fontSize = 16.sp)
        }
    }
}

/* ────────────────────────────  PREVIEW  ─────────────────────────── */

@Preview(showBackground = true)
@Composable
fun HelloL4ScreenPreview() {
    HelloL4Screen(navigateBack = {}, navigateToNext = {})
}
