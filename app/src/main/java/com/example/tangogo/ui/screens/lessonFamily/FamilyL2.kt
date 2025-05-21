package com.example.tangogo.ui.screens.lessonFamily

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.tangogo.ui.screens.lessonHello.QuestionImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyL2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
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

            // Section Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Lesson 4", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("かぞく", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

                Text("2.もじとことば", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Justify)
                Text("Letters and words", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(20.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("かぞくの かず。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Family Count", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            FamilyTextCard(R.drawable.hitori, "ひとり", "hitori", "One person", R.raw.hitori)
            FamilyTextCard(R.drawable.futari, "ふたり", "futari", "Two people", R.raw.futari)
            FamilyTextCard(R.drawable.san, "さんにん", "san-nin", "Three people", R.raw.sannin)
            FamilyTextCard(R.drawable.yo, "よにん", "yo-nin", "Four people", R.raw.yonin)
            FamilyTextCard(R.drawable.go, "ごにん", "go-nin", "Five people", R.raw.gonin)
            FamilyTextCard(R.drawable.roku, "ろくにん", "roku-nin", "Six people", R.raw.rokunin)

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("かぞくは なんにんですか。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("How many people are there in your family?", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            QuestionImageCard(
                question = "かぞく は なんにん ですか。",
                romaji = "Kazoku wa nan-nin desu ka.",
                options = listOf("a.ひとり", "b.さんにん"),
                correctAnswer = "b.さんにん",
                imageResId = R.drawable.san
            )

            QuestionImageCard(
                question = "かぞく は なんにん ですか。",
                romaji = "Kazoku wa nan-nin desu ka.",
                options = listOf("a.よにん", "b.ろくにん"),
                correctAnswer = "a.よにん",
                imageResId = R.drawable.yo
            )

            QuestionImageCard(
                question = "かぞく は なんにん ですか。",
                romaji = "Kazoku wa nan-nin desu ka.",
                options = listOf("a.よにん", "b.ごにん"),
                correctAnswer = "b.ごにん",
                imageResId = R.drawable.go
            )

            QuestionImageCard(
                question = "かぞく は なんにん ですか。",
                romaji = "Kazoku wa nan-nin desu ka.",
                options = listOf("a.さんにん", "b.ふたり"),
                correctAnswer = "b.ふたり",
                imageResId = R.drawable.futari
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

@Composable
fun FamilyTextCard(
    imageResId: Int,
    word: String,
    reading: String,
    meaning: String,
    soundResId: Int
) {
    val context = LocalContext.current

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
                        .clickable {
                            playAudio(context, soundResId)
                        }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                androidx.compose.foundation.Image(
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

fun playAudio(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener { it.release() }
    mediaPlayer.start()
}

@Preview(showBackground = true)
@Composable
fun FamilyL2ScreenPreview() {
    FamilyL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
