package com.example.tangogo.ui.screens.lessonHiragana

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
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
fun HiraganaL1Screen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current

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
                            text = "ひらがな",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToDashboard()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Dashboard",
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
                    text = "ひらがな を よみましょう",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "Let's read Hiragana.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            HiraganaTextCard(
                imageResId = R.drawable.asa_img,
                word = "あさ",
                reading = "asa",
                meaning = "Morning",
                soundResId = R.raw.asa,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.yoru_img,
                word = "よる",
                reading = "yoru",
                meaning = "Evening/Night",
                soundResId = R.raw.yoru,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.tsukue_img,
                word = "つくえ",
                reading = "tsukue",
                meaning = "Desk",
                soundResId = R.raw.tsukue,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.yasai_img,
                word = "やさい",
                reading = "yasai",
                meaning = "Vegetable",
                soundResId = R.raw.yasai,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.sakana_img,
                word = "さかな",
                reading = "sakana",
                meaning = "Fish",
                soundResId = R.raw.sakana,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.tamago_img,
                word = "さかな",
                reading = "tamago",
                meaning = "Egg",
                soundResId = R.raw.tamago,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.kazoku_img,
                word = "かぞく",
                reading = "kazoku",
                meaning = "Family",
                soundResId = R.raw.kazoku,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.nihongo_img,
                word = "にほんご",
                reading = "nihongo",
                meaning = "Japanese language",
                soundResId = R.raw.nihongo,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.inu_img,
                word = "いぬ",
                reading = "inu",
                meaning = "Dog",
                soundResId = R.raw.inu,
                context = context
            )

            HiraganaTextCard(
                imageResId = R.drawable.neko_img,
                word = "ねこ",
                reading = "neko",
                meaning = "Cat",
                soundResId = R.raw.neko,
                context = context
            )
        }
    }
}

@Composable
fun HiraganaTextCard(
    imageResId: Int,
    word: String,
    reading: String,
    meaning: String,
    soundResId: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDFCFB)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
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
                            playAudio3(context, soundResId)
                        }
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
                    Text(
                        text = word,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F3F3F),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = reading,
                        fontSize = 16.sp,
                        color = Color(0xFF757575),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = meaning,
                        fontSize = 16.sp,
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun playAudio3(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener { it.release() }
    mediaPlayer.start()
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HiraganaL1ScreenPreview() {
    HiraganaL1Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToNext = {}
    )
}
