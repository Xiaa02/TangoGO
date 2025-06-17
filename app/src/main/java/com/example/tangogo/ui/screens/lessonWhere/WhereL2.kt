package com.example.tangogo.ui.screens.lessonWhere

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
import com.example.tangogo.ui.screens.lessonFood.FoodTextCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereL2Screen(
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
                    "レストラン",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text("Restaurant", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Food list
            FoodTextCard(R.drawable.udonya, "うどんや", "udon'ya", "udon shop", R.raw.udonya, playAudio)
            FoodTextCard(R.drawable.sobaya, "そばや", "soba'ya", "soba shop", R.raw.sobaya, playAudio)
            FoodTextCard(R.drawable.sushiya, "すしや", "sushi'ya", "sushi shop", R.raw.sushiya, playAudio)
            FoodTextCard(R.drawable.ramenya, "ラーメンや", "raamen'ya", "ramen shop", R.raw.ramenya, playAudio)
            FoodTextCard(R.drawable.pizaya, "ピザや", "piza'ya", "pizza shop", R.raw.pizaya, playAudio)
            FoodTextCard(R.drawable.curryya, "カレーや", "karee'ya", "curry shop", R.raw.kareeya, playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            // Grammar Section
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("ノート", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Note.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

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
                    Text("うどんやさん", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("udon'ya-san", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("ラーメンやさん", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("raamen'ya-san", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("おすしやさん", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("osushiya-san", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("おそばやさん", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("osobaya-san", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(color = Color.Gray, thickness = 1.dp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("や (ya) added to a food name means it’s a shop selling that food.", fontSize = 16.sp, color = Color(0xFF3F3F3F))
                    Text("さん (san) added after the shop name is a polite way to refer to it.", fontSize = 16.sp, color = Color(0xFF3F3F3F))
                    Text("お (o) before words like すし or そば is an honorific prefix to make the word more polite.", fontSize = 16.sp, color = Color(0xFF3F3F3F))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    "みせの なまえは なんですか。",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "Choose the name of the restaurant.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "a.すしや",
                imageResId = R.drawable.sushiya
            )

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "b.そばや",
                imageResId = R.drawable.sobaya
            )

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "c.うどんや",
                imageResId = R.drawable.udonya
            )

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "d.ラーメンや",
                imageResId = R.drawable.ramenya
            )

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "e.ピザや",
                imageResId = R.drawable.pizaya
            )

            QuestionImage2Card(
                options = listOf("a.すしや", "b.そばや", "c.うどんや", "d.ラーメンや", "e.ピザや", "f.カレーや"),
                correctAnswer = "f.カレーや",
                imageResId = R.drawable.curryya
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
fun WhereL2ScreenPreview() {
    WhereL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}