@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonFood

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodL3Screen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToLessonComplete: () -> Unit
) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Single playAudio function controlling MediaPlayer instance
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
                            text = "たべもの",
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
                Text("Lesson 5", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("なにが すきですか", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            DialogCard(
                jpLine1 = "あべ：ホセさん、いつも あさごはん を たべますか。",
                romaji1 = "Abe: Hose-san, itsumo asa-gohan o tabemasu ka?",
                jpLine2 = "ホセ：はい、たべます。",
                romaji2 = "Hose: Hai, tabemasu.",
                jpLine3 = "あべ：なにを　よく　たべますか。",
                romaji3 = "Abe: Nani o yoku tabemasu ka?",
                jpLine4 = "ホセ：わたしは パンと　くだものを　よく　たべます。",
                romaji4 = "Hose: Watashi wa pan to kudamono o yoku tabemasu.",
                jpLine5 = "あべ：卵は？",
                romaji5 = "Abe: Tamago wa?",
                jpLine6 = "ホセ：卵は　あまり　たべません。",
                romaji6 = "Hose: Tamago wa amari tabemasen.",
                engLine1 = "Abe: Hose-san, do you always eat breakfast?",
                engLine2 = "Hose: Yes, I do.",
                engLine3 = "Abe: What do you often eat?",
                engLine4 = "Hose: I often eat bread and fruit.",
                engLine5 = "Abe: How about eggs?",
                engLine6 = "Hose: I don’t eat eggs very often.",
                onPlayAudio = { playAudio(R.raw.kaiwa061) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("つかわれている ぶんぽう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Grammar used.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAE6FF)),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("____ を ____ ます。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("____ o ____ masu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("____ は ____ ません。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("____ wa ____ masen.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 1.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("____ を ____ ますか。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("____ o ____ masu ka?", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("なに を ____ ますか。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("nani o ____ masu ka?", fontSize = 16.sp, color = Color(0xFF757575))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDAE6FF)),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("よく____ます。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("Yoku____masu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 1.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Text("あまり____ません。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("Amari____masen.", fontSize = 16.sp, color = Color(0xFF757575))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "ききましょう。4にんは　あさごはんを　たべますか。たべませんか。",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Listen. Do the four people eat, or not eat breakfast?",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            BreakfastPreferenceTable(
                context = context,
                playAudio = { resId -> playAudio(resId) }
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
fun DialogCard(
    jpLine1: String,
    romaji1: String,
    jpLine2: String,
    romaji2: String,
    jpLine3: String,
    romaji3: String,
    jpLine4: String,
    romaji4: String,
    jpLine5: String,
    romaji5: String,
    jpLine6: String,
    romaji6: String,
    engLine1: String,
    engLine2: String,
    engLine3: String,
    engLine4: String,
    engLine5: String,
    engLine6: String,
    onPlayAudio: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
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
                    modifier = Modifier.clickable { onPlayAudio() }.size(22.dp)
                )
            }

            Text(jpLine1, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji1, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine2, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji2, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine3, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji3, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine4, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji4, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine5, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji5, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text(jpLine6, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(romaji6, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color.LightGray)

            Text(engLine1, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine2, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine3, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine4, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine5, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(engLine6, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun BreakfastPreferenceTable(
    context: Context = LocalContext.current,
    playAudio: (Int) -> Unit
) {
    val people = listOf(
        Pair("1. かわいさん", "Kawai-san"),
        Pair("2. たなかさん", "Tanaka-san"),
        Pair("3. キムさん", "Kimu-san"),
        Pair("4. カーラさん", "Kaara-san")
    )

    val correctAnswers = mapOf(
        "かわいさん" to true,
        "たなかさん" to false,
        "キムさん" to true,
        "カーラさん" to false
    )

    val audioMap = mapOf(
        "かわいさん" to R.raw.kaiwa062_kawai_san,
        "たなかさん" to R.raw.kaiwa062_tanaka_san,
        "キムさん" to R.raw.kaiwa062_kimu_san,
        "カーラさん" to R.raw.kaiwa062_kaara_san,
    )

    val selections = remember { mutableStateMapOf<String, Boolean?>() }
    val correctnessMap = remember { mutableStateMapOf<String, Boolean?>() }

    fun playFeedbackSound(isCorrect: Boolean) {
        val soundRes = if (isCorrect) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        people.forEach { (label, romajiName) ->
            val key = label.substringAfter(". ").trim() // e.g., かわいさん
            if (!selections.containsKey(key)) {
                selections[key] = null
                correctnessMap[key] = null
            }

            val selected = selections[key]
            val isCorrect = correctnessMap[key]
            val audioResId = audioMap[key]

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (isCorrect) {
                        true -> Color(0xFFE0FAE9)
                        false -> Color(0xFFFFEBEE)
                        else -> Color(0xFFFDFCFB)
                    }
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(romajiName, fontSize = 16.sp, color = Color.Gray)
                        }
                        if (audioResId != null) {
                            IconButton(onClick = { playAudio(audioResId) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Play Audio",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = selected == true,
                            onCheckedChange = {
                                selections[key] = true
                                val correct = correctAnswers[key] == true
                                correctnessMap[key] = correct
                                playFeedbackSound(correct)
                            }
                        )
                        Text("あさごはんを　たべます。", fontWeight = FontWeight.Medium)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = selected == false,
                            onCheckedChange = {
                                selections[key] = false
                                val correct = correctAnswers[key] == false
                                correctnessMap[key] = correct
                                playFeedbackSound(correct)
                            }
                        )
                        Text("あさごはんは　たべません。", fontWeight = FontWeight.Medium)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    isCorrect?.let {
                        Text(
                            text = if (it) "⭕ Correct!" else "❌ Try again.",
                            color = if (it) Color(0xFF388E3C) else Color(0xFFD32F2F),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodL3ScreenPreview() {
    FoodL3Screen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToLessonComplete = {}
    )
}
