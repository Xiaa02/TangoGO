package com.example.tangogo.ui.screens.memoryKatakana

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatakanaChartScreen(
    navigateToDashboard: () -> Unit,
    onCharClick: (String) -> Unit
) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "カタカナ",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateToDashboard) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = navigateToDashboard) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Dashboard"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))

            Text(
                text = "アイウエオ表",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 1.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Katakana Chart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 1.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "V = Vowels   C = Consonant",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Box(
                            Modifier.size(40.dp).background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) { Text("V/C", fontSize = 14.sp, color = Color.DarkGray) }

                        listOf("a", "i", "u", "e", "o").forEach { vowel ->
                            Box(
                                Modifier.size(40.dp).background(getVowelColor(vowel), RoundedCornerShape(8.dp))
                                    .clickable { onCharClick(vowel) },
                                contentAlignment = Alignment.Center
                            ) { Text(text = vowel, fontSize = 16.sp, color = Color.White) }
                        }
                    }
                }

                itemsIndexed(table) { rowIndex, rowData ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Box(
                            Modifier.size(40.dp)
                                .background(if (consonants[rowIndex].isNotEmpty()) Color(0xFF9B1C1C) else Color.Transparent, RoundedCornerShape(8.dp))
                                .then(if (consonants[rowIndex].isNotEmpty()) Modifier.clickable { onCharClick(consonants[rowIndex]) } else Modifier),
                            contentAlignment = Alignment.Center
                        ) { if (consonants[rowIndex].isNotEmpty()) Text(text = consonants[rowIndex], fontSize = 16.sp, color = Color.White) }

                        rowData.forEach { char ->
                            Box(
                                Modifier.size(40.dp).background(if (char.isNotEmpty()) Color(0xFFF1F1F1) else Color.Transparent, RoundedCornerShape(8.dp))
                                    .then(if (char.isNotEmpty()) Modifier.clickable { onCharClick(char) } else Modifier),
                                contentAlignment = Alignment.Center
                            ) { if (char.isNotEmpty()) Text(text = char, fontSize = 18.sp) }
                        }
                    }
                }
            }
        }
    }

    BackHandler(onBack = navigateToDashboard)
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
fun KatakanaChartScreenPreview() {
    KatakanaChartScreen(
        navigateToDashboard = {},
        onCharClick = { /* handle navigation for character */ }
    )
}