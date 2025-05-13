package com.example.tangogo.ui.screens.lessonHiragana

import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun HiraganaTableScreen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit,
    onCharClick: (String) -> Unit = {}
) {
    val context = LocalContext.current

    fun playSound(char: String) {
        val soundResId = when (char) {
            // Sei-on
            "あ" -> R.raw.a; "い" -> R.raw.i; "う" -> R.raw.u; "え" -> R.raw.e; "お" -> R.raw.o
            "か" -> R.raw.ka; "き" -> R.raw.ki; "く" -> R.raw.ku; "け" -> R.raw.ke; "こ" -> R.raw.ko
            "さ" -> R.raw.sa; "し" -> R.raw.shi; "す" -> R.raw.su; "せ" -> R.raw.se; "そ" -> R.raw.so
            "た" -> R.raw.ta; "ち" -> R.raw.chi; "つ" -> R.raw.tsu; "て" -> R.raw.te; "と" -> R.raw.to
            "な" -> R.raw.na; "に" -> R.raw.ni; "ぬ" -> R.raw.nu; "ね" -> R.raw.ne; "の" -> R.raw.no
            "は" -> R.raw.ha; "ひ" -> R.raw.hi; "ふ" -> R.raw.fu; "へ" -> R.raw.he; "ほ" -> R.raw.ho
            "ま" -> R.raw.ma; "み" -> R.raw.mi; "む" -> R.raw.mu; "め" -> R.raw.me; "も" -> R.raw.mo
            "や" -> R.raw.ya; "ゆ" -> R.raw.yu; "よ" -> R.raw.yo
            "ら" -> R.raw.ra; "り" -> R.raw.ri; "る" -> R.raw.ru; "れ" -> R.raw.re; "ろ" -> R.raw.ro
            "わ" -> R.raw.wa; "を" -> R.raw.wo; "ん" -> R.raw.n

            // Daku-on
            "が" -> R.raw.ga; "ぎ" -> R.raw.gi; "ぐ" -> R.raw.gu; "げ" -> R.raw.ge; "ご" -> R.raw.go
            "ざ" -> R.raw.za; "じ" -> R.raw.ji; "ず" -> R.raw.zu; "ぜ" -> R.raw.ze; "ぞ" -> R.raw.zo
            "だ" -> R.raw.da; "ぢ" -> R.raw.di; "づ" -> R.raw.du; "で" -> R.raw.de; "ど" -> R.raw.do_hira
            "ば" -> R.raw.ba; "び" -> R.raw.bi; "ぶ" -> R.raw.bu; "べ" -> R.raw.be; "ぼ" -> R.raw.bo

            // Handaku-on
            "ぱ" -> R.raw.pa; "ぴ" -> R.raw.pi; "ぷ" -> R.raw.pu; "ぺ" -> R.raw.pe; "ぽ" -> R.raw.po

            else -> null
        }

        soundResId?.let {
            val mediaPlayer = MediaPlayer.create(context, it)
            mediaPlayer.setOnCompletionListener { player -> player.release() }
            mediaPlayer.start()
        }
    }

    val consonants = listOf("", "k", "s", "t", "n", "h", "m", "y", "r", "w", "N")
    val table = listOf(
        listOf("あ", "い", "う", "え", "お"),
        listOf("か", "き", "く", "け", "こ"),
        listOf("さ", "し", "す", "せ", "そ"),
        listOf("た", "ち", "つ", "て", "と"),
        listOf("な", "に", "ぬ", "ね", "の"),
        listOf("は", "ひ", "ふ", "へ", "ほ"),
        listOf("ま", "み", "む", "め", "も"),
        listOf("や", "",  "ゆ", "",  "よ"),
        listOf("ら", "り", "る", "れ", "ろ"),
        listOf("わ", "",  "",   "",  "を"),
        listOf("ん", "",  "",   "",  "")
    )

    val dakuonConsonants = listOf("g", "z", "d", "b")
    val dakuonTable = listOf(
        listOf("が", "ぎ", "ぐ", "げ", "ご"),
        listOf("ざ", "じ", "ず", "ぜ", "ぞ"),
        listOf("だ", "ぢ", "づ", "で", "ど"),
        listOf("ば", "び", "ぶ", "べ", "ぼ")
    )

    val handakuonConsonants = listOf("p")
    val handakuonTable = listOf(
        listOf("ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("ひらがな", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))

            Text("あいうえお表", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(start = 20.dp))
            Text("Hiragana Chart", fontSize = 20.sp, color = Color.DarkGray, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, bottom = 8.dp))

            Spacer(Modifier.height(12.dp))
            Text("Sei-on", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 8.dp))

            Text("V = Vowels   C = Consonant", fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 6.dp))

            // Vowel Header
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(bottom = 8.dp)) {
                Box(Modifier.size(40.dp).background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                    Text("V/C", fontSize = 14.sp, color = Color.DarkGray)
                }
                listOf("a", "i", "u", "e", "o").forEach { vowel ->
                    Box(
                        Modifier.size(40.dp).background(getVowelColor(vowel), RoundedCornerShape(8.dp))
                            .clickable { playSound(vowel); onCharClick(vowel) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(vowel, fontSize = 16.sp, color = Color.White)
                    }
                }
            }

            // Basic Table
            table.forEachIndexed { i, row ->
                val rowLabel = consonants[i]
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(vertical = 4.dp)) {
                    Box(
                        Modifier.size(40.dp).background(if (rowLabel.isNotEmpty()) Color(0xFF9B1C1C) else Color.Transparent, RoundedCornerShape(8.dp))
                            .then(if (rowLabel.isNotEmpty()) Modifier.clickable { playSound(rowLabel); onCharClick(rowLabel) } else Modifier),
                        contentAlignment = Alignment.Center
                    ) {
                        if (rowLabel.isNotEmpty()) Text(rowLabel, fontSize = 16.sp, color = Color.White)
                    }
                    row.forEach { char ->
                        Box(
                            modifier = Modifier.size(40.dp).background(if (char.isNotEmpty()) Color(0xFFF1F1F1) else Color.Transparent, RoundedCornerShape(8.dp))
                                .then(if (char.isNotEmpty()) Modifier.clickable { playSound(char); onCharClick(char) } else Modifier),
                            contentAlignment = Alignment.Center
                        ) {
                            if (char.isNotEmpty()) Text(char, fontSize = 18.sp)
                        }
                    }
                }
            }

            // Daku-on
            Text("Daku-on", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(top = 20.dp, start = 20.dp))
            dakuonTable.forEachIndexed { i, row ->
                val rowLabel = dakuonConsonants[i]
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(vertical = 4.dp)) {
                    Box(
                        Modifier.size(40.dp).background(Color(0xFF004D40), RoundedCornerShape(8.dp)).clickable { playSound(rowLabel); onCharClick(rowLabel) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(rowLabel, fontSize = 16.sp, color = Color.White)
                    }
                    row.forEach { char ->
                        Box(
                            modifier = Modifier.size(40.dp).background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp)).clickable { playSound(char); onCharClick(char) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(char, fontSize = 18.sp)
                        }
                    }
                }
            }

            // Handaku-on
            Text("Handaku-on", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(top = 20.dp, start = 20.dp))
            handakuonTable.forEachIndexed { i, row ->
                val rowLabel = handakuonConsonants[i]
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(vertical = 4.dp)) {
                    Box(
                        Modifier.size(40.dp).background(Color(0xFF880E4F), RoundedCornerShape(8.dp)).clickable { playSound(rowLabel); onCharClick(rowLabel) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(rowLabel, fontSize = 16.sp, color = Color.White)
                    }
                    row.forEach { char ->
                        Box(
                            modifier = Modifier.size(40.dp).background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp)).clickable { playSound(char); onCharClick(char) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(char, fontSize = 18.sp)
                        }
                    }
                }
            }

            Spacer(Modifier.height(40.dp))

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

            Spacer(Modifier.height(32.dp))
        }
    }

    BackHandler(onBack = navigateBack)
}

private fun getVowelColor(vowel: String): Color = when (vowel) {
    "a" -> Color(0xFF1E3170)
    "i" -> Color(0xFF093D16)
    "u" -> Color(0xFF983677)
    "e" -> Color(0xFFB77F0F)
    "o" -> Color(0xFF515C9B)
    else -> Color.Gray
}

@Preview(showBackground = true)
@Composable
fun HiraganaTableScreenPreview() {
    HiraganaTableScreen(
        navigateBack = {},
        navigateToNext = {},
        onCharClick = {})
}
