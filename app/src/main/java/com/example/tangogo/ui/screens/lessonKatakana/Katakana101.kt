package com.example.tangogo.ui.screens.lessonKatakana

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun Katakana101Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
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
                            text = "カタカナ",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Lesson 2",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "カタカナ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Katakana 101",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Katakana (カタカナ) is one of the three Japanese writing systems that consist of 46 basic symbols.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF444444),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Insert additional text here
                Text(
                    text = "Katakana is primarily used to write foreign words, names, and onomatopoeia. It is also used for emphasis and some loanwords.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF444444),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            Text(
                text = "Foreign Words",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )

            KatakanaItem(
                word = "ケーキ",
                reading = "keeki",
                explanation = "Cake is from english word.",
                example = "Example: ケーキ (keeki) meaning 'cake'."
            )

            KatakanaItem(
                word = "マラッカ",
                reading = "marakka",
                explanation = "Used to represents a place name, a common use of Katakana for geographical locations.",
                example = "Example: マラッカ (marakka) — Melaka."
            )

            KatakanaItem(
                word = "アルバイト",
                reading = "arubaito",
                explanation = "Katakana is used to spell foreign words.",
                example = "Example: アルバイト (arubaito) means part-time job."
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Onomatopoeia",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )

            KatakanaItem(
                word = "にゃあにゃあ",
                reading = "nyaanyaa",
                explanation = "Onomatopoeia: Animal sounds, like the sound of a cat.",
                example = "Example: にゃあにゃあ (nyaanyaa) — sound of a cat"
            )

            KatakanaItem(
                word = "もーもー",
                reading = "moomoo",
                explanation = "Onomatopoeia: Animal sounds, like the sound of a cow.",
                example = "Example: もーもー (moomoo) — sound of a cow"
            )

            KatakanaItem(
                word = "ワンワン",
                reading = "wanwan",
                explanation = "Onomatopoeia: Animal sounds, like the sound of a dog.",
                example = "Example: ワンワン (wanwan) — sound of a dog"
            )

            KatakanaItem(
                word = "ザザザ",
                reading = "zaazaa",
                explanation = "Onomatopoeia: A heavy rain sound.",
                example = "Example: ザザザ (zaazaa) — sound of heavy rain"
            )

            KatakanaItem(
                word = "トントン",
                reading = "tonton",
                explanation = "Onomatopoeia: The sound of a door knocking.",
                example = "Example: トントン (tonton) — sound of a door knocking"
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
                    .align(Alignment.CenterHorizontally)
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}



@Composable
fun KatakanaItem(
    word: String,
    reading: String,
    explanation: String,
    example: String
) {
    var expanded by remember { mutableStateOf(false) }

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
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = word,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF3F3F3F)
                    )
                    Text(
                        text = reading,
                        fontSize = 18.sp,
                        color = Color(0xFF757575)
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = Color(0xFF757575)
                    )
                }
            }

            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = explanation,
                        fontSize = 16.sp,
                        color = Color(0xFF444444)
                    )

                    if (example.isNotBlank()) {
                        Text(
                            text = example,
                            fontSize = 16.sp,
                            color = Color(0xFF5C6BC0),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Katakana101ScreenPreview() {
    Katakana101Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
