package com.example.tangogo.ui.screens.lessonFamily

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyL1Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    fun playAudio(resId: Int) {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
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
                            text = "かぞく",
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

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("わたしの かぞく", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("My Family", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Family members from わたしの かぞく
            FamilyTextCard(R.drawable.father, "ちち", "chichi", "Father", R.raw.chichi, ::playAudio)
            FamilyTextCard(R.drawable.mother, "はは", "haha", "Mother", R.raw.haha, ::playAudio)
            FamilyTextCard(R.drawable.brother_older, "あに", "ani", "Older brother", R.raw.ani, ::playAudio)
            FamilyTextCard(R.drawable.sister_older, "あね", "ane", "Older sister", R.raw.ane, ::playAudio)
            FamilyTextCard(R.drawable.me, "わたし", "watashi", "Me / I", R.raw.watashi, ::playAudio)
            FamilyTextCard(R.drawable.brother_younger, "おとうと", "otooto", "Younger brother", R.raw.otooto,
                ::playAudio)
            FamilyTextCard(R.drawable.sister_younger, "いもうと", "imooto", "Younger sister", R.raw.imooto,
                ::playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            // Second family section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("すずき まりさんの かぞく", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("(Mari Suzuki's family)", fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Family members from すずき まりさんの かぞく
            FamilyTextCard(R.drawable.father_img, "おとうさん", "otoosan", "Father", R.raw.otoosan,
                ::playAudio)
            FamilyTextCard(R.drawable.mother_img, "おかあさん", "okaasan", "Mother", R.raw.okaasan,
                ::playAudio)
            FamilyTextCard(R.drawable.brother_older_img, "おにいさん", "oniisan", "Older brother", R.raw.oniisan,
                ::playAudio)
            FamilyTextCard(R.drawable.sister_older_img, "おねえさん", "oneesan", "Older sister", R.raw.oneesan,
                ::playAudio)
            FamilyTextCard(R.drawable.mari, "まりさん", "Mari-san", "Mari", R.raw.mari, ::playAudio)
            FamilyTextCard(R.drawable.brother_younger_img, "おとうとさん", "otootosan", "Younger brother", R.raw.otootosan,
                ::playAudio)
            FamilyTextCard(R.drawable.sister_younger_img, "いもうとさん", "imootosan", "Younger sister", R.raw.imootosan,
                ::playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("じぶんの かぞく", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Casual / Own Family", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(10.dp))

            FamilyTextCard(R.drawable.husband_casual, "おっと", "otto", "Husband (casual)", R.raw.otto,
                ::playAudio)
            FamilyTextCard(R.drawable.wife_casual, "つま", "tsuma", "Wife (casual)", R.raw.tsuma, ::playAudio)
            FamilyTextCard(R.drawable.child_casual, "こども", "kodomo", "Child (casual)", R.raw.kodomo,
                ::playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("たにんの かぞく", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Polite / Someone Else’s Family", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            FamilyTextCard(R.drawable.husband_polite, "ごしゅじん", "goshujin", "Husband (polite)", R.raw.goshujin,
                ::playAudio)
            FamilyTextCard(R.drawable.wife_polite, "おくさん", "okusan", "Wife (polite)", R.raw.okusan,
                ::playAudio)
            FamilyTextCard(R.drawable.child_polite, "おこさん", "okosan", "Child (polite)", R.raw.okosan,
                ::playAudio)

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
    soundResId: Int,
    playAudio: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFCFB)),
        //elevation = CardDefaults.cardElevation(4.dp),
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
                        .clickable { playAudio(soundResId) }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
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

@Preview(showBackground = true)
@Composable
fun FamilyL1ScreenPreview() {
    FamilyL1Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
