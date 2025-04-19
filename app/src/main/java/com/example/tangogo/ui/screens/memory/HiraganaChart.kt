package com.example.tangogo.ui.screens.memory

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiraganaTableScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit
) {
    val consonants = listOf("", "k", "s", "t", "n", "h", "m", "y", "r", "w", "N")
    val vowels = listOf("a", "i", "u", "e", "o")
    val table = listOf(
        listOf("あ", "い", "う", "え", "お"),
        listOf("か", "き", "く", "け", "こ"),
        listOf("さ", "し", "す", "せ", "そ"),
        listOf("た", "ち", "つ", "て", "と"),
        listOf("な", "に", "ぬ", "ね", "の"),
        listOf("は", "ひ", "ふ", "へ", "ほ"),
        listOf("ま", "み", "む", "め", "も"),
        listOf("や", "", "ゆ", "", "よ"),
        listOf("ら", "り", "る", "れ", "ろ"),
        listOf("わ", "", "", "", "を"),
        listOf("ん", "", "", "", "")
    )

    Scaffold(
        topBar = {
            TopAppBar(
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
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navigateToDashboard() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Logout"
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "あいうえお表",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Hiragana Chart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 1.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "V = Vowels  C = Consonant",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "V/C", fontSize = 14.sp, color = Color.Black, lineHeight = 12.sp, textAlign = TextAlign.Center)
                        }

                        vowels.forEach {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(getVowelColor(it), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = it, fontSize = 16.sp, color = Color.White)
                            }
                        }
                    }
                }

                items(consonants.size) { rowIndex ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        val rowLabel = consonants[rowIndex]
                        val rowData = table[rowIndex]

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    if (rowLabel.isNotEmpty()) Color(0xFF9B1C1C) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (rowLabel.isNotEmpty()) {
                                Text(text = rowLabel, fontSize = 16.sp, color = Color.White)
                            }
                        }

                        rowData.forEach { char ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        if (char.isNotEmpty()) Color(0xFFF1F1F1) else Color.Transparent,
                                        RoundedCornerShape(8.dp)
                                    ),
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

    BackHandler(onBack = navigateBack)
}

private fun getVowelColor(vowel: String): Color {
    return when (vowel) {
        "a" -> Color(0xFF1E3170)
        "i" -> Color(0xFF093D16)
        "u" -> Color(0xFF983677)
        "e" -> Color(0xFFB77F0F)
        "o" -> Color(0xFF515C9B)
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun HiraganaTableScreenPreview() {
    HiraganaTableScreen(
        navigateBack = {},
        navigateToDashboard = {}
    )
}