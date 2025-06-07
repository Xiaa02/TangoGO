package com.example.tangogo.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@Composable
fun AboutDevScreen(
    navigateBack: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Top bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { navigateBack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color(0xFF3F3F3F)
                    )
                }

                Text(
                    text = "About Developer",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Avatar
            Image(
                painter = painterResource(id = R.drawable.a194474),
                contentDescription = "Developer Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(100.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Greeting and Name
            Text(text = "Hello, I'm", fontSize = 16.sp, color = Color.Gray)
            Text(
                text = "Nurin Pishol",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Fullstack Developer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF666666)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Experience + Education Section
            ExperienceAndEducationSection()

            Spacer(modifier = Modifier.height(32.dp))

            // Contact buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { uriHandler.openUri("mailto:nurinpishol@gmail.com") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000000))
                ) {
                    Icon(Icons.Default.Email, contentDescription = "Email", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Email Me", color = Color.White)
                }

                Button(
                    onClick = { uriHandler.openUri("https://github.com/Xiaa02") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "GitHub",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("GitHub", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Copyright © Nurin Pishol 2025",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ExperienceAndEducationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Experience Card
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Experience", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("2+ years", color = Color.Gray)
            Text("Fullstack ", color = Color.Gray)
            Text("Development", color = Color.Gray)
        }

        // Education Card
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Education", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("B.Sc Bachelors Degree in Computer Science", color = Color.Gray)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Bottom paragraph centered
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "I created TangoGO to help A1 level learners master Japanese characters in a fun, engaging, and effective way — focused on Hiragana, Katakana & Kanji.",
            color = Color(0xFF444444),
            lineHeight = 22.sp,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AboutDevScreenPreview() {
    AboutDevScreen()
}
