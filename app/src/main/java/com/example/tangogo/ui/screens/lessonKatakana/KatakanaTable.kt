package com.example.tangogo.ui.screens.lessonKatakana

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
fun KatakanaTableScreen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit,
    onCharClick: (String) -> Unit = {}
) {
    val context = LocalContext.current

    fun playSound(char: String) {
        val soundResId = when (char) {
            // Sei-on
            "ア" -> R.raw.a; "イ" -> R.raw.i; "ウ" -> R.raw.u
            "エ" -> R.raw.e; "オ" -> R.raw.o
            "カ" -> R.raw.ka; "キ" -> R.raw.ki; "ク" -> R.raw.ku; "ケ" -> R.raw.ke; "コ" -> R.raw.ko
            "サ" -> R.raw.sa; "シ" -> R.raw.shi; "ス" -> R.raw.su; "セ" -> R.raw.se; "ソ" -> R.raw.so
            "タ" -> R.raw.ta; "チ" -> R.raw.chi; "ツ" -> R.raw.tsu; "テ" -> R.raw.te; "ト" -> R.raw.to
            "ナ" -> R.raw.na; "ニ" -> R.raw.ni; "ヌ" -> R.raw.nu; "ネ" -> R.raw.ne; "ノ" -> R.raw.no
            "ハ" -> R.raw.ha; "ヒ" -> R.raw.hi; "フ" -> R.raw.fu; "ヘ" -> R.raw.he; "ホ" -> R.raw.ho
            "マ" -> R.raw.ma; "ミ" -> R.raw.mi; "ム" -> R.raw.mu; "メ" -> R.raw.me; "モ" -> R.raw.mo
            "ヤ" -> R.raw.ya; "ユ" -> R.raw.yu; "ヨ" -> R.raw.yo
            "ラ" -> R.raw.ra; "リ" -> R.raw.ri; "ル" -> R.raw.ru; "レ" -> R.raw.re; "ロ" -> R.raw.ro
            "ワ" -> R.raw.wa; "ヲ" -> R.raw.wo; "ン" -> R.raw.n

            // Daku-on
            "ガ" -> R.raw.ga; "ギ" -> R.raw.gi; "グ" -> R.raw.gu; "ゲ" -> R.raw.ge; "ゴ" -> R.raw.go
            "ザ" -> R.raw.za; "ジ" -> R.raw.ji; "ズ" -> R.raw.zu; "ゼ" -> R.raw.ze; "ゾ" -> R.raw.zo
            "ダ" -> R.raw.da; "ヂ" -> R.raw.di; "ヅ" -> R.raw.du; "デ" -> R.raw.de; "ド" -> R.raw.do_hira

            // Handaku-on
            "バ" -> R.raw.ba; "ビ" -> R.raw.bi; "ブ" -> R.raw.bu; "ベ" -> R.raw.be; "ボ" -> R.raw.bo
            "パ" -> R.raw.pa; "ピ" -> R.raw.pi; "プ" -> R.raw.pu; "ペ" -> R.raw.pe; "ポ" -> R.raw.po

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
        listOf("ア", "イ", "ウ", "エ", "オ"),
        listOf("カ", "キ", "ク", "ケ", "コ"),
        listOf("サ", "シ", "ス", "セ", "ソ"),
        listOf("タ", "チ", "ツ", "テ", "ト"),
        listOf("ナ", "ニ", "ヌ", "ネ", "ノ"),
        listOf("ハ", "ヒ", "フ", "ヘ", "ホ"),
        listOf("マ", "ミ", "ム", "メ", "モ"),
        listOf("ヤ", "",  "ユ", "",  "ヨ"),
        listOf("ラ", "リ", "ル", "レ", "ロ"),
        listOf("ワ", "",  "",   "",  "ヲ"),
        listOf("ン", "",  "",   "",  "")
    )

    val dakuonConsonants = listOf("g", "z", "d", "b")
    val dakuonTable = listOf(
        listOf("ガ", "ギ", "グ", "ゲ", "ゴ"),
        listOf("ザ", "ジ", "ズ", "ゼ", "ゾ"),
        listOf("ダ", "ヂ", "ヅ", "デ", "ド"),
        listOf("バ", "ビ", "ブ", "ベ", "ボ")
    )

    val handakuonConsonants = listOf("p")
    val handakuonTable = listOf(
        listOf("パ", "ピ", "プ", "ペ", "ポ")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("カタカナ", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = navigateToNext) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_next), contentDescription = "Logout")
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

            Text("アイウエオ表", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(start = 20.dp))
            Text("Katakana Chart", fontSize = 20.sp, color = Color.DarkGray, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, bottom = 8.dp))

            Spacer(Modifier.height(12.dp))
            Text("Sei-on", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 8.dp))

            Text("V = Vowels   C = Consonant", fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 6.dp))

            // Vowel Header
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(bottom = 8.dp)) {
                Box(Modifier.size(40.dp).background(Color(0xFFFCF5FD), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                    Text("V/C", fontSize = 14.sp, color = Color.DarkGray)
                }
                listOf("a", "i", "u", "e", "o").forEach { vowel ->
                    Box(
                        Modifier.size(40.dp).background(getVowelColor(vowel), RoundedCornerShape(8.dp)).clickable {
                            playSound(vowel); onCharClick(vowel)
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(vowel, fontSize = 16.sp, color = Color.White)
                    }
                }
            }

            // Base Table
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
                            modifier = Modifier
                                .size(40.dp)
                                .then(if (char.isNotEmpty()) Modifier.shadow(4.dp, RoundedCornerShape(8.dp)) else Modifier)
                                .background(if (char.isNotEmpty()) Color(0xFFFFFFFF) else Color.Transparent, RoundedCornerShape(8.dp))
                                .then(if (char.isNotEmpty()) Modifier.clickable { playSound(char); onCharClick(char) } else Modifier),
                            contentAlignment = Alignment.Center
                        ) {
                            if (char.isNotEmpty()) Text(char, fontSize = 18.sp)
                        }
                    }
                }
            }

            // Daku-on Section
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
                            modifier = Modifier
                                .size(40.dp)
                                .then(if (char.isNotEmpty()) Modifier.shadow(4.dp, RoundedCornerShape(8.dp)) else Modifier)
                                .background(if (char.isNotEmpty()) Color(0xFFFFFFFF) else Color.Transparent, RoundedCornerShape(8.dp))
                                .then(if (char.isNotEmpty()) Modifier.clickable { playSound(char); onCharClick(char) } else Modifier),
                            contentAlignment = Alignment.Center
                        ) {
                            if (char.isNotEmpty()) Text(char, fontSize = 18.sp)
                        }
                    }
                }
            }

            // Handaku-on Section
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
                            modifier = Modifier
                                .size(40.dp)
                                .then(if (char.isNotEmpty()) Modifier.shadow(4.dp, RoundedCornerShape(8.dp)) else Modifier)
                                .background(if (char.isNotEmpty()) Color(0xFFFFFFFF) else Color.Transparent, RoundedCornerShape(8.dp))
                                .then(if (char.isNotEmpty()) Modifier.clickable { playSound(char); onCharClick(char) } else Modifier),
                            contentAlignment = Alignment.Center
                        ) {
                            if (char.isNotEmpty()) Text(char, fontSize = 18.sp)
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
fun KatakanaTableScreenPreview() {
    KatakanaTableScreen(
        navigateBack = {},
        navigateToNext = {},
        onCharClick = {}
    )
}
