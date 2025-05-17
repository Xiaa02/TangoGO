package com.example.tangogo.ui.screens.memoryKanji

import android.widget.VideoView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.example.tangogo.R
import com.example.tangogo.ui.screens.memoryKatakana.playSound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IchijihanKanjiMnemonicScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToKanjiChart: () -> Unit,
    onMnemonicClick: () -> Unit,
    onStrokeClick: () -> Unit,
    onWriteClick: () -> Unit,
) {
    val backgroundColor = Color(0xFFC8F7C5)
    val buttonColor = Color(0xFFFFFFFF)
    val iconSize = 32.dp
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Memory Hints",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateToKanjiChart() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Chart"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navigateToDashboard() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Home"
                        )
                    }
                }
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
                .padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .width(300.dp)
                    .padding(start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("かんじ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Kanji", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1時半（いちじはん）",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "ichijihan - 1 o'clock and a half",
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color(0xFFFAF6FA), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { ctx ->
                        VideoView(ctx).apply {
                            setVideoURI("android.resource://${ctx.packageName}/${R.raw.ichijihan_mnemonic}".toUri())
                            setOnPreparedListener { it.isLooping = true }
                            start()
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            playSound(context, R.raw.ichijihan)
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            @Composable
            fun ButtonWithIcon(text: String, icon: Painter, onClick: () -> Unit) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(buttonColor, RoundedCornerShape(16.dp))
                            .clickable { onClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = text,
                            modifier = Modifier.size(iconSize),
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text, fontSize = 12.sp, color = Color.Black)
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonWithIcon(
                    text = "Mnemonic",
                    icon = painterResource(id = R.drawable.ic_lightbulb),
                    onClick = onMnemonicClick
                )
                ButtonWithIcon(
                    text = "Stroke Order",
                    icon = painterResource(id = R.drawable.ic_question),
                    onClick = onStrokeClick
                )
                ButtonWithIcon(
                    text = "Write it!",
                    icon = painterResource(id = R.drawable.ic_write),
                    onClick = onWriteClick
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }

    BackHandler(onBack = navigateBack)
}

@Preview(showBackground = true)
@Composable
fun IchijihanKanjiMnemonicPreview() {
    IchijihanKanjiMnemonicScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToKanjiChart = {},
        onMnemonicClick = {},
        onStrokeClick = {},
        onWriteClick = {}
    )
}
