package com.example.tangogo.ui.screens.lessonHello

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloL2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current

    // Shared MediaPlayer state
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Shared playAudio lambda
    val playAudio: (Int) -> Unit = { resId ->
        mediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        mediaPlayer = MediaPlayer.create(context, resId).apply {
            setOnCompletionListener {
                it.release()
                mediaPlayer = null
            }
            start()
        }
    }

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
                            text = "こんにちは",
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

            // Section Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Lesson 3", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("どうぞ よろしく", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)

                Spacer(modifier = Modifier.height(10.dp))

                Text("2.もじとことば", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Justify)
                Text("Letters and words", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(20.dp))

                Text("しごとと せんもん", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Justify)
                Text("Occupations and Professions", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, textAlign = TextAlign.Justify)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Use modified HelloTextCard which accepts playAudio lambda
            HelloTextCard(R.drawable.student_img, "がくせい", "Gakusei", "Student", R.raw.gakusei, playAudio)
            HelloTextCard(R.drawable.teacher_img, "きょうし", "Kyoushi", "Teacher", R.raw.kyoushi, playAudio)
            HelloTextCard(R.drawable.housewife_img, "しゅふ", "Shufu", "Housewife", R.raw.shufu, playAudio)
            HelloTextCard(R.drawable.employee_img, "かいしゃいん", "Kaishain", "Company Employee", R.raw.kaishain, playAudio)
            HelloTextCard(R.drawable.civil_servant_img, "こうむいん", "Koumuin", "Civil Servant", R.raw.koumuin, playAudio)
            HelloTextCard(R.drawable.engineer_img, "エンジニア", "Enjinia", "Engineer", R.raw.enjinia, playAudio)

            Spacer(modifier = Modifier.height(32.dp))

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

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HelloL2ScreenPreview() {
    HelloL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
