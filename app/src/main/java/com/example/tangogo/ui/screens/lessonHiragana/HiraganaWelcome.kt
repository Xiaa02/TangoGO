package com.example.tangogo.ui.screens.lessonHiragana

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiraganaWelcomeScreen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val textPrimary = Color(0xFF000000)
    val textSecondary = Color(0xFF555555)

    Scaffold(
        containerColor = Color(0xFFF3F0FF),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFF3F3F3F)
                ),
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
                    IconButton(onClick = navigateBack) {
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            // Large top-left circle
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .offset(x = (-100).dp, y = 100.dp)
                    .shadow(8.dp, CircleShape)
                    .background(Color(0xFFECEAFF), CircleShape)
            )

            // Smaller bottom-right circle
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .offset(x = 250.dp, y = 450.dp)
                    .shadow(4.dp, CircleShape)
                    .background(Color(0xFFECEAFF), CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Section Title
                Text(
                    "Lesson 1",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    "ひらがな",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    "Hiragana",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(50.dp))

                // What you'll learn section
                Text(
                    text = "What you'll learn",
                    color = textPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val learningPoints = listOf(
                    "ひらがなを よむ ほうほう。" to "How to read Hiragana.",
                    "ひらがなの おん。" to "Pronunciation of Hiragana sounds.",
                    "かんたんな ことば。" to "Simple words using Hiragana."
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    learningPoints.forEach { (japanese, english) ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(textPrimary, shape = RoundedCornerShape(50))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = japanese,
                                    color = textPrimary,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = english,
                                    color = textSecondary,
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Continue Button fixed at bottom center with padding below
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HiraganaWelcomeScreenPreview() {
    HiraganaWelcomeScreen(
        navigateBack = {},
        navigateToNext = {}
    )
}
