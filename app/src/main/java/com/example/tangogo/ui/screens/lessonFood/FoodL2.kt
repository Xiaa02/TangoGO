@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonFood

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
fun FoodL2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current

    // Shared MediaPlayer state
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
                            text = "たべもの",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
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
            // Section Title and Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Lesson 5", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("なにが すきですか", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "2.かいわと ぶんぽう",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Justify
                )
                Text(
                    "Conversation and Grammar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text("Let's listen.", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            DialogCard(
                jpLine1 = "ジョイ：たべものは　なにが　すきですか。",
                romaji1 = "Joi: Tabemono wa nani ga suki desu ka?",
                jpLine2 = "たなか：にくが　すきです。さかなも　すきです。",
                romaji2 = "Tanaka: Niku ga suki desu. Sakana mo suki desu.",
                jpLine3 = "ジョイ：やさいは？",
                romaji3 = "Joi: Yasai wa?",
                jpLine4 = "たなか：やさいは　すきじゃないです。",
                romaji4 = "Tanaka: Yasai wa sukijanai desu.",
                jpLine5 = "ジョイ：わたしは　にくと　やさいが　すきです。",
                romaji5 = "Joi: Watashi wa niku to yasai ga suki desu.",
                engLine1 = "Joi: What food do you like?",
                engLine2 = "Tanaka: I like meat. I also like fish.",
                engLine3 = "Joi: Vegetables?",
                engLine4 = "Tanaka: I don’t like vegetables.",
                engLine5 = "Joi: I like meat and vegetables.",
                playAudio = playAudio
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Grammar Section
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
                    Text("____ が　すきです。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("____ ga suki desu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("____ は　すきじゃないです。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("____ wa sukijanai desu.", fontSize = 16.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(color = Color.Gray, thickness = 1.dp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("なにが　すきですか。", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                    Text("Nani ga suki desu ka.", fontSize = 16.sp, color = Color(0xFF757575))

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Audio play button row for the whole dialogue
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
                    Text("ききましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        "Listen and choose: What drinks do the people like and dislike?",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }

                IconButton(
                    onClick = { playAudio(R.raw.kaiwa059) }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Play Sound",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            DrinkPreferenceTable(
                playAudio = playAudio
            )

            Spacer(modifier = Modifier.height(32.dp))

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
    engLine1: String,
    engLine2: String,
    engLine3: String,
    engLine4: String,
    engLine5: String,
    playAudio: (Int) -> Unit
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
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { playAudio(R.raw.kaiwa058) }
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkPreferenceTable(
    playAudio: (Int) -> Unit
) {
    val people = listOf(
        Pair("1. シンさん", "Shin-san"),
        Pair("2. あべさん", "Abe-san"),
        Pair("3. よしださん", "Yoshida-san"),
        Pair("4. ジョイさん", "Joi-san"),
        Pair("5. ホセさん", "Hose-san")
    )
    val drinkOptions = listOf("コーヒー", "こうちゃ", "おちゃ", "ジュース", "ワイン", "ビール", "ユー")

    val correctAnswers = mapOf(
        "シンさん" to Pair("コーヒー", "こうちゃ"),
        "あべさん" to Pair("こうちゃ", "おちゃ"),
        "よしださん" to Pair("ワイン", "ビール"),
        "ジョイさん" to Pair("ジュース", "ユー"),
        "ホセさん" to Pair("おちゃ", "ジュース")
    )

    val preferences = remember { mutableStateMapOf<String, Pair<String?, String?>>() }
    val correctnessMap = remember { mutableStateMapOf<String, Boolean?>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        people.forEach { (jpName, romajiName) ->
            if (!preferences.containsKey(jpName)) {
                preferences[jpName] = Pair(null, null)
                correctnessMap[jpName] = null
            }

            val selected = preferences[jpName]
            val isCorrect = correctnessMap[jpName]

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
                Column(Modifier.padding(16.dp)) {
                    Text(jpName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(romajiName, fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("すきです", fontWeight = FontWeight.SemiBold)
                            Text("Suki desu", fontWeight = FontWeight.Normal)

                            var expanded1 by remember { mutableStateOf(false) }
                            val selected1 = selected?.first ?: ""

                            ExposedDropdownMenuBox(
                                expanded = expanded1,
                                onExpandedChange = { expanded1 = !expanded1 }
                            ) {
                                OutlinedTextField(
                                    value = selected1,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Select") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded1) },
                                    modifier = Modifier.menuAnchor().fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded1,
                                    onDismissRequest = { expanded1 = false }
                                ) {
                                    drinkOptions.forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                preferences[jpName] =
                                                    preferences[jpName]?.copy(first = option) ?: Pair(option, null)
                                                expanded1 = false

                                                val second = preferences[jpName]?.second
                                                if (!option.isNullOrEmpty() && !second.isNullOrEmpty()) {
                                                    val result = Pair(option, second) == correctAnswers[jpName]
                                                    correctnessMap[jpName] = result
                                                    playAudio(if(result) R.raw.ding else R.raw.error)
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text("すきじゃないです", fontWeight = FontWeight.SemiBold)
                            Text("Sukijanai desu", fontWeight = FontWeight.Normal)

                            var expanded2 by remember { mutableStateOf(false) }
                            val selected2 = selected?.second ?: ""

                            ExposedDropdownMenuBox(
                                expanded = expanded2,
                                onExpandedChange = { expanded2 = !expanded2 }
                            ) {
                                OutlinedTextField(
                                    value = selected2,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Select") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded2) },
                                    modifier = Modifier.menuAnchor().fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded2,
                                    onDismissRequest = { expanded2 = false }
                                ) {
                                    drinkOptions.forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                preferences[jpName] =
                                                    preferences[jpName]?.copy(second = option) ?: Pair(null, option)
                                                expanded2 = false

                                                val first = preferences[jpName]?.first
                                                if (!first.isNullOrEmpty() && !option.isNullOrEmpty()) {
                                                    val result = Pair(first, option) == correctAnswers[jpName]
                                                    correctnessMap[jpName] = result
                                                    playAudio(if(result) R.raw.ding else R.raw.error)
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

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
fun FoodL2ScreenPreview() {
    FoodL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
