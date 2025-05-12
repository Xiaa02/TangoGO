package com.example.tangogo.ui.screens.memoryKatakana

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoKatakanaMnemonicScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToKatakanaChart: () -> Unit,
    onMnemonicClick: () -> Unit,
    onStrokeClick: () -> Unit,
    onWriteClick: () -> Unit,
) {
    val backgroundColor = Color(0xFFFFF59D)
    val buttonColor = Color(0xFFFFFFFF)
    val iconSize = 32.dp
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Memory Hints", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateToKatakanaChart() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Chart")
                    }
                },
                actions = {
                    IconButton(onClick = { navigateToDashboard() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Home")
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
                modifier = Modifier.width(300.dp).padding(start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("カタカナ", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Katakana", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("ヲ - w(o)", fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 20.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wo_katakana_memory),
                    contentDescription = "Katakana ヲ",
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    contentScale = ContentScale.Fit
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(28.dp).clickable {
                        playSound(context, R.raw.wo)
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
                        Icon(painter = icon, contentDescription = text, modifier = Modifier.size(iconSize), tint = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text, fontSize = 12.sp, color = Color.Black)
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonWithIcon("Mnemonic", painterResource(id = R.drawable.ic_lightbulb), onMnemonicClick)
                ButtonWithIcon("Stroke Order", painterResource(id = R.drawable.ic_question), onStrokeClick)
                ButtonWithIcon("Write it!", painterResource(id = R.drawable.ic_write), onClick = onWriteClick)
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }

    BackHandler(onBack = navigateBack)
}

@Preview(showBackground = true)
@Composable
fun WoKatakanaMnemonicPreview() {
    WoKatakanaMnemonicScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToKatakanaChart = {},
        onMnemonicClick = {},
        onStrokeClick = {},
        onWriteClick = {}
    )
}
