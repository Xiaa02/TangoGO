package com.example.tangogo.ui.screens.memoryHiragana

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
import com.example.tangogo.common.composable.ButtonWithIcon
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiHiraganaMemoryScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToHiraganaChart: () -> Unit,
    onMnemonicClick: () -> Unit,
    onStrokeClick: () -> Unit,
    onWriteClick: () -> Unit
) {
    val backgroundColor = Color(0xFFFFC6CC)
    
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
                    IconButton(onClick = { navigateToHiraganaChart() }) {
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
                Text("ひらがな", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Hiragana", fontSize = 18.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ち - chi",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hiragana Box with sound icon
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("ち", fontSize = 200.sp, fontWeight = FontWeight.Normal)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            playSound(context, R.raw.chi)
                        }
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

           

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
fun ChiHiraganaMemoryScreenPreview() {
    ChiHiraganaMemoryScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToHiraganaChart = {},
        onMnemonicClick = {},
        onStrokeClick = {},
        onWriteClick = {}
    )
}
