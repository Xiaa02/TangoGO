package com.example.tangogo.ui.screens.memoryKanji

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.ui.draw.shadow

data class KanjiSection(
    val titleJP: String,
    val titleEN: String,
    val color: Color,
    val kanjiList: List<String>
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun KanjiChartScreen(
    navigateToDashboard: () -> Unit,
    onCharClick: (String) -> Unit
) {
    val kanjiByLesson = listOf(
        KanjiSection("たべもの", "Food", Color(0xFF093D16), listOf("魚", "肉", "水", "食")),
        KanjiSection("いえ", "Home", Color(0xFFFF9800), listOf("大", "小")),
        KanjiSection("せいかつ", "Life", Color(0xFFA83760), listOf("時", "半", "月", "火", "水", "木", "金", "土", "日"))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "かんじ表",
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
                text = "かんじ表",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, bottom = 1.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Kanji Chart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 1.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 9.dp, top = 1.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                kanjiByLesson.forEach { section ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(section.color, RoundedCornerShape(6.dp))
                                    .padding(vertical = 6.dp, horizontal = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Column {
                                    Text(
                                        text = section.titleJP,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = section.titleEN,
                                        color = Color.White.copy(alpha = 0.9f),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(17.dp),
                                verticalArrangement = Arrangement.spacedBy(17.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                section.kanjiList.forEach { kanji ->
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .shadow(4.dp, RoundedCornerShape(8.dp))
                                            .background(Color(0xFFF1F1F1), RoundedCornerShape(8.dp))
                                            .clickable { onCharClick(kanji) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = kanji, fontSize = 22.sp)
                                    }
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

@Preview(showBackground = true)
@Composable
fun KanjiChartScreenPreview() {
    KanjiChartScreen(
        navigateToDashboard = {},
        onCharClick = {}
    )
}
