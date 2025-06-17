package com.example.tangogo.ui.screens.lessonHome

import android.media.MediaPlayer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeL2Screen(
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
                            text = "いえ",
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
                    "Lesson 7",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "いい へや ですね",
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
                    "いえ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Home", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Home space list
            HomeTextCard(R.drawable.house, "いえ", "ie", "house", R.raw.ie, playAudio)
            HomeTextCard(R.drawable.bathroom, "おふろ", "ofuro", "bathroom", R.raw.ofuro, playAudio)
            HomeTextCard(R.drawable.entrance, "げんかん", "genkan", "entrance", R.raw.genkan, playAudio)
            HomeTextCard(R.drawable.kitchen, "だいどころ", "daidokoro", "kitchen", R.raw.daidokoro, playAudio)
            HomeTextCard(R.drawable.toilet_img, "トイレ", "toire", "toilet", R.raw.toire, playAudio)
            HomeTextCard(R.drawable.garden, "にわ", "niwa", "garden", R.raw.niwa, playAudio)
            HomeTextCard(R.drawable.room, "へや", "heya", "room", R.raw.heya, playAudio)

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

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "a.いえ",
                imageResId = R.drawable.house
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "b.おふろ",
                imageResId = R.drawable.bathroom
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "c.げんかん",
                imageResId = R.drawable.entrance
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "d.だいどころ",
                imageResId = R.drawable.kitchen
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "e.トイレ",
                imageResId = R.drawable.toilet_img
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "f.にわ",
                imageResId = R.drawable.garden
            )

            QuestionImage3Card(
                options = listOf("a.いえ", "b.おふろ", "c.げんかん", "d.だいどころ", "e.トイレ", "f.にわ", "g.へや"),
                correctAnswer = "g.へや",
                imageResId = R.drawable.room
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


@Preview(showBackground = true)
@Composable
fun HomeL2ScreenPreview() {
    HomeL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}