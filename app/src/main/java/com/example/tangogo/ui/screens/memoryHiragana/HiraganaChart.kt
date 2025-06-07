package com.example.tangogo.ui.screens.memoryHiragana

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
import androidx.compose.ui.draw.shadow
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
fun HiraganaChartScreen(
    navigateToDashboard: () -> Unit,
    onCharClick: (String) -> Unit
) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "ひらがな",
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
                text = "あいうえお表",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 1.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Hiragana Chart",
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
                // Vowel header row
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Box(
                            Modifier
                                .size(40.dp)
                                .background(Color(0xFFFCF5FD), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "V/C",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Center
                            )
                        }
                        listOf("a", "i", "u", "e", "o").forEach { vowel ->
                            Box(
                                Modifier
                                    .size(40.dp)
                                    .background(getVowelColor(vowel), RoundedCornerShape(8.dp))
                                    .clickable { onCharClick(vowel) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = vowel, fontSize = 16.sp, color = Color.White)
                            }
                        }
                    }
                }

                // Consonant rows
                itemsIndexed(table) { rowIndex, rowData ->
                    val rowLabel = consonants[rowIndex]

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        // Row header (consonant)
                        Box(
                            Modifier
                                .size(40.dp)
                                .background(
                                    if (rowLabel.isNotEmpty()) Color(0xFF9B1C1C) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                                .then(
                                    if (rowLabel.isNotEmpty())
                                        Modifier.clickable { onCharClick(rowLabel) }
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (rowLabel.isNotEmpty()) {
                                Text(text = rowLabel, fontSize = 16.sp, color = Color.White)
                            }
                        }

                        // Character cells
                        rowData.forEach { char ->
                            val baseModifier = Modifier
                                .size(40.dp)
                                .then(if (char.isNotEmpty()) Modifier.shadow(4.dp, RoundedCornerShape(8.dp)) else Modifier)
                                .background(
                                    if (char.isNotEmpty()) Color(0xFFF1F1F1) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )

                            Box(
                                modifier = if (char.isNotEmpty())
                                    baseModifier.clickable { onCharClick(char) }
                                else baseModifier,
                                contentAlignment = Alignment.Center
                            ) {
                                if (char.isNotEmpty()) {
                                    Text(text = char, fontSize = 18.sp)
                                }
                            }
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
fun HiraganaChartScreenPreview() {
    HiraganaChartScreen(
        navigateToDashboard = {},
        onCharClick = { /* handle navigation for character */ },
    )
}
