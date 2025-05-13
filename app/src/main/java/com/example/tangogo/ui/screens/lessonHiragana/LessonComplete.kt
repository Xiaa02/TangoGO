package com.example.tangogo.ui.screens.lessonHiragana

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LessonCompleteScreen(
    navigateToDashboard: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Large top-left circle
        Box(
            modifier = Modifier
                .size(400.dp)
                .offset(x = (-100).dp, y = (100).dp)
                .shadow(8.dp, CircleShape)
                .background(Color(0xFFECEAFF), CircleShape)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF9DCEFF), Color(0xFFCC8FED))
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                        )
                    ) {
                        append("Lesson\nCompleted !")
                    }
                },
                lineHeight = 40.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 140.dp, top = 10.dp)
                    .offset(y = 150.dp)
            )


        }

        // Smaller bottom-right circle
        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = (250).dp, y = (450).dp)
                .shadow(4.dp, CircleShape)
                .background(Color(0xFFECEAFF), CircleShape)
        )

        // Continue Button at Bottom Center
        Button(
            onClick = navigateToDashboard,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF061428),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .fillMaxWidth(0.9f)
                .height(56.dp)
                .shadow(10.dp, RoundedCornerShape(50))
        ) {
            Text(
                text = "Continue",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonCompleteScreenPreview() {
    LessonCompleteScreen(
        navigateToDashboard = {}
    )
}
