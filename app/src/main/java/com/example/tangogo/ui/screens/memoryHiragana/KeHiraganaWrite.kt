package com.example.tangogo.ui.screens.memoryHiragana

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.common.composable.ButtonWithIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeHiraganaWriteScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToHiraganaChart: () -> Unit,
    onMnemonicClick: () -> Unit = {},
    onStrokeClick: () -> Unit = {},
    onWriteClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFFFC6CC)

    val context = LocalContext.current
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf(Path()) }

    var showCorrectPopup by remember { mutableStateOf(false) }
    var showWrongPopup by remember { mutableStateOf(false) }

    val correctSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )
    val wrongSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )

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
                text = "け - ke",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.size(300.dp).background(Color.White, RoundedCornerShape(16.dp))
                ) {
                    Canvas(
                        Modifier.fillMaxSize().pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { offset -> currentPath.moveTo(offset.x, offset.y) },
                                onDrag = { change, _ -> currentPath.lineTo(change.position.x, change.position.y) },
                                onDragEnd = {
                                    paths.add(currentPath)
                                    currentPath = Path()
                                }
                            )
                        }
                    ) {
                        val w = size.width
                        val h = size.height
                        val cx = w / 2f
                        val cy = h / 2f
                        val inset = 16.dp.toPx()
                        val dash = PathEffect.dashPathEffect(floatArrayOf(8.dp.toPx(), 8.dp.toPx()), 0f)

                        drawLine(Color.LightGray, Offset(cx, inset), Offset(cx, h - inset), 2.dp.toPx(), pathEffect = dash)
                        drawLine(Color.LightGray, Offset(inset, cy), Offset(w - inset, cy), 2.dp.toPx(), pathEffect = dash)

                        drawIntoCanvas {
                            val paint = android.graphics.Paint().apply {
                                isAntiAlias = true
                                color = android.graphics.Color.LTGRAY
                                textSize = h * 0.8f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                            it.nativeCanvas.drawText("け", cx, cy + paint.textSize / 3f, paint)
                        }

                        val brush = 16.dp.toPx()
                        paths.forEach {
                            drawPath(it, Color.Black.copy(alpha = 0.8f), style = Stroke(brush, cap = StrokeCap.Round, join = StrokeJoin.Round))
                        }
                        drawPath(currentPath, Color.Red.copy(alpha = 0.8f), style = Stroke(brush, cap = StrokeCap.Round, join = StrokeJoin.Round))
                    }

                    Icon(Icons.AutoMirrored.Filled.VolumeUp, "Play", tint = Color.Unspecified, modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(28.dp).clickable {
                        playSound(context, R.raw.ke)
                    })

                    Icon(Icons.Default.Delete, "Clear", modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp).size(28.dp).clickable {
                        paths.clear()
                        currentPath = Path()
                        showCorrectPopup = false
                        showWrongPopup = false
                    })
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (paths.size == 3) {
                            playSound(context, R.raw.ding)
                            showCorrectPopup = true
                        } else {
                            playSound(context, R.raw.incorrect)
                            showWrongPopup = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8583CC)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Check", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                ButtonWithIcon("Mnemonic", painterResource(id = R.drawable.ic_lightbulb), onMnemonicClick)
                ButtonWithIcon("Stroke Order", painterResource(id = R.drawable.ic_question), onStrokeClick)
                ButtonWithIcon("Write it!", painterResource(id = R.drawable.ic_write), onWriteClick)
            }
        }

        if (showCorrectPopup) {
            BackHandler(enabled = true) {}
            ModalBottomSheet(
                onDismissRequest = {},
                sheetState = correctSheetState,
                containerColor = Color.White,
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⭕ Good job!", color = Color(0xFF4CAF50), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text("Perfect! Keep it up.", fontSize = 16.sp)
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            showCorrectPopup = false
                            paths.clear()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("CONTINUE", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }

        if (showWrongPopup) {
            BackHandler(enabled = true) {}
            ModalBottomSheet(
                onDismissRequest = {},
                sheetState = wrongSheetState,
                containerColor = Color.White,
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("❌ Incorrect", color = Color(0xFFE57373), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text("Hint:", fontSize = 16.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("Check the stroke order and try again.", fontSize = 18.sp)
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = { showWrongPopup = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("GOT IT", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }

    BackHandler(onBack = navigateBack)
}

@Preview(showBackground = true)
@Composable
fun KeHiraganaWriteScreenPreview() {
    KeHiraganaWriteScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToHiraganaChart = {},
        onMnemonicClick = {},
        onStrokeClick = {},
        onWriteClick = {}
    )
}
