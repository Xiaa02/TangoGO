@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.lessonFood

//import androidx.activity.compose.BackHandler
import android.content.Context
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodL1Screen(
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
                            text = "たべもの",
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
                Text("Lesson 5", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("なにが すきですか", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("たべもの", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Food", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            // Food list
            FoodTextCard(R.drawable.gohan, "ごはん", "gohan", "rice", R.raw.gohan, playAudio)
            FoodTextCard(R.drawable.mizu, "みず", "mizu", "water", R.raw.mizu, playAudio)
            FoodTextCard(R.drawable.bread_img, "パン", "pan", "bread", R.raw.pan, playAudio)
            FoodTextCard(R.drawable.niku, "にく", "niku", "meat", R.raw.niku, playAudio)
            FoodTextCard(R.drawable.sakana_img, "さかな", "sakana", "fish", R.raw.sakana, playAudio)
            FoodTextCard(R.drawable.gyuunyuu, "ぎゅうにゅう", "gyuunyuu", "milk", R.raw.gyuunyuu, playAudio)
            FoodTextCard(R.drawable.tamago_img, "たまご", "tamago", "egg", R.raw.tamago, playAudio)
            FoodTextCard(R.drawable.yasai_img, "やさい", "yasai", "vegetable", R.raw.yasai, playAudio)
            FoodTextCard(R.drawable.coffee_img, "コーヒー", "koohii", "coffee", R.raw.koohii, playAudio)
            FoodTextCard(R.drawable.wain, "ワイン", "wain", "wine", R.raw.wain, playAudio)
            FoodTextCard(R.drawable.kudamono, "くだもの", "kudamono", "fruit", R.raw.kudamono, playAudio)
            FoodTextCard(R.drawable.misoshiru, "みそしる", "misoshiru", "miso soup", R.raw.misoshiru, playAudio)

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("なにを　たべますか。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Which things do you eat?", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                FoodMultipleSelectQuiz(context = context)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("なにを　のみますか。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Which things do you drink?", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
                DrinkMultipleSelectQuiz(context = context)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("きいて　こたえを　えらびましょう。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Listen and choose the answer.", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            ListenSelect2Card(
                question     = "1",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "a. にく",
                soundResId    = R.raw.niku
            )

            ListenSelect2Card(
                question     = "2",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "e. やさい",
                soundResId    = R.raw.yasai
            )

            ListenSelect2Card(
                question     = "3",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "f. たべもの",
                soundResId    = R.raw.tabemono
            )

            ListenSelect2Card(
                question     = "4",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "b. おちゃ",
                soundResId    = R.raw.ocha
            )

            ListenSelect2Card(
                question     = "5",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "c. パン",
                soundResId    = R.raw.pan
            )

            ListenSelect2Card(
                question     = "6",
                options      = listOf(
                    "a. にく",
                    "b. おちゃ",
                    "c. パン",
                    "d. コーヒー",
                    "e. やさい",
                    "f. たべもの"
                ),
                correctAnswer = "d. コーヒー",
                soundResId    = R.raw.koohii
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
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("    魚　     肉　     卵　    水", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F))
                        Text("  sakana     niku     tamago     mizu", fontSize = 16.sp, color = Color(0xFF757575))

                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text("1–4 は、どの え (a–d) ですか。", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Match the kanji 1–4 to the pictures", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(10.dp))

            KanjiQuestionCard(
                question = "1",
                options = listOf("魚", "肉", "卵", "水"),
                correctAnswer = "水",
                imageResId = R.drawable.mizu_kanji
            )

            KanjiQuestionCard(
                question = "2",
                options = listOf("魚", "肉", "卵", "水"),
                correctAnswer = "魚",
                imageResId = R.drawable.sakana_kanji
            )

            KanjiQuestionCard(
                question = "3",
                options = listOf("魚", "肉", "卵", "水"),
                correctAnswer = "肉",
                imageResId = R.drawable.niku_kanji
            )

            KanjiQuestionCard(
                question = "4",
                options = listOf("魚", "肉", "卵", "水"),
                correctAnswer = "卵",
                imageResId = R.drawable.tamago_kanji
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

data class FoodItem(
    val imageResId: Int,
    val word: String,
    val reading: String,
    val meaning: String,
    val soundResId: Int
)

@Composable
fun FoodTextCard(
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

@Composable
fun FoodMultipleSelectQuiz(context: Context) {
    val foodOptions = listOf(
        FoodItem(R.drawable.gohan, "ごはん", "gohan", "rice", R.raw.gohan),
        FoodItem(R.drawable.misoshiru, "みそしる", "misoshiru", "miso soup", R.raw.misoshiru),
        FoodItem(R.drawable.coffee_img, "コーヒー", "koohii", "coffee", R.raw.koohii),
        FoodItem(R.drawable.bread_img, "パン", "pan", "bread", R.raw.pan),
        FoodItem(R.drawable.tamago_img, "たまご", "tamago", "egg", R.raw.tamago),
        FoodItem(R.drawable.gyuunyuu, "ぎゅうにゅう", "gyuunyuu", "milk", R.raw.gyuunyuu)
    )
    val correctAnswers = listOf("ごはん", "たまご","パン")

    var selected by remember { mutableStateOf(listOf<String>()) }
    var showFeedback by remember { mutableStateOf<String?>(null) }

    Column {
        foodOptions.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                row.forEach { item ->
                    val isSelected = selected.contains(item.word)
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp)
                            .clickable {
                                selected = selected.toMutableList().apply {
                                    if (contains(item.word)) remove(item.word) else add(item.word)
                                }
                                playAudio(context, item.soundResId)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFFEADCF4) else Color(0xFFFDFCFB)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.imageResId),
                                contentDescription = item.meaning,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(item.word, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(item.meaning, fontSize = 14.sp, color = Color.DarkGray)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                val isCorrect = selected.sorted() == correctAnswers.sorted()
                playAudio(context, if (isCorrect) R.raw.ding else R.raw.incorrect)
                showFeedback = if (isCorrect) "⭕ Correct! ごはん, みそしる = Food!" else "❌ Try again!"
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E8BE7)),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Check Answer", fontWeight = FontWeight.Bold, color = Color.White)
        }

        showFeedback?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = if (it.startsWith("⭕")) Color(0xFF4CAF50) else Color(0xFFE57373),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun DrinkMultipleSelectQuiz(context: Context) {
    val drinkOptions = listOf(
        FoodItem(R.drawable.mizu, "みず", "mizu", "water", R.raw.mizu),
        FoodItem(R.drawable.coffee_img, "コーヒー", "koohii", "coffee", R.raw.koohii),
        FoodItem(R.drawable.gyuunyuu, "ぎゅうにゅう", "gyuunyuu", "milk", R.raw.gyuunyuu),
        FoodItem(R.drawable.kudamono, "くだもの", "kudamono", "fruit", R.raw.kudamono),
        FoodItem(R.drawable.gohan, "ごはん", "gohan", "rice", R.raw.gohan),
        FoodItem(R.drawable.niku, "にく", "niku", "meat", R.raw.niku)
    )

    val correctAnswers = listOf("みず", "コーヒー", "ぎゅうにゅう")

    var selected by remember { mutableStateOf(listOf<String>()) }
    var showFeedback by remember { mutableStateOf<String?>(null) }

    Column {
        drinkOptions.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                row.forEach { item ->
                    val isSelected = selected.contains(item.word)
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp)
                            .clickable {
                                selected = selected.toMutableList().apply {
                                    if (contains(item.word)) remove(item.word) else add(item.word)
                                }
                                playAudio(context, item.soundResId)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFFDEF1FF) else Color(0xFFFDFCFB)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.imageResId),
                                contentDescription = item.meaning,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(item.word, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(item.meaning, fontSize = 14.sp, color = Color.DarkGray)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                val isCorrect = selected.sorted() == correctAnswers.sorted()
                playAudio(context, if (isCorrect) R.raw.ding else R.raw.incorrect)
                showFeedback = if (isCorrect) "⭕ Correct! みず, コーヒー, ぎゅうにゅう,ワイン = Drinks!" else "❌ Some are not drinks!"
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E8BE7)),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Check Answer", fontWeight = FontWeight.Bold, color = Color.White)
        }

        showFeedback?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = if (it.startsWith("⭕")) Color(0xFF4CAF50) else Color(0xFFE57373),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListenSelect2Card(
    question: String,
    options: List<String>,
    correctAnswer: String,
    soundResId: Int,
) {
    val context = LocalContext.current
    var selectedAnswer by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val isPreview = LocalInspectionMode.current
    val mediaPlayer = remember {
        if (!isPreview) MediaPlayer.create(context, soundResId) else null
    }

    fun playFeedbackSound(correct: Boolean) {
        val soundRes = if (correct) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (isCorrect) {
                true -> Color(0xFFE0FAE9) // correct - light green
                false -> Color(0xFFFFEBEE) // wrong - light red
                else -> Color(0xFFFDFCFB)
            }
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val questionNumber = question.substringBefore('\n')

                Text(
                    text = questionNumber,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )

                IconButton(onClick = {
                    mediaPlayer?.let {
                        if (it.isPlaying) {
                            it.pause()
                            it.seekTo(0)
                        }
                        it.start()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Play Sound",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedAnswer,
                    onValueChange = {},
                    label = { Text("Select answer") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedAnswer = option
                                val correct = option == correctAnswer
                                isCorrect = correct
                                playFeedbackSound(correct)
                                expanded = false
                            }
                        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiQuestionCard(
    question: String,
    options: List<String>,
    correctAnswer: String,
    imageResId: Int? = null
) {
    val context = LocalContext.current
    var selectedAnswer by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var expanded by remember { mutableStateOf(false) }

    fun playFeedbackSound(correct: Boolean) {
        val soundRes = if (correct) R.raw.ding else R.raw.error
        MediaPlayer.create(context, soundRes)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (isCorrect) {
                true -> Color(0xFFE0FAE9)
                false -> Color(0xFFFFEBEE)
                else -> Color(0xFFFDFCFB)
            }
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            imageResId?.let { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = question,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F3F3F)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedAnswer,
                    onValueChange = {},
                    label = { Text("Select answer") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedAnswer = option
                                val correct = option == correctAnswer
                                isCorrect = correct
                                playFeedbackSound(correct)
                                expanded = false
                            }
                        )
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

fun playAudio(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener { it.release() }
    mediaPlayer.start()
}

@Preview(showBackground = true)
@Composable
fun FoodL1ScreenPreview() {
    FoodL1Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
