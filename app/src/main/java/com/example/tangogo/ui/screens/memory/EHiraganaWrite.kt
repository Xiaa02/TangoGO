package com.example.tangogo.ui.screens.memory

import android.content.Context
import android.media.MediaPlayer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EHiraganaWriteScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToHiraganaChart: () -> Unit,
    onMnemonicClick: () -> Unit = {},
    onStrokeClick: () -> Unit = {},
    onWriteClick: () -> Unit = {}
) {
    val context = LocalContext.current

    // store user strokes
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf(Path()) }

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
                    containerColor = Color(0xFFFFC6CC),
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateToHiraganaChart () }) {
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
        containerColor = Color(0xFFFFC6CC)
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFFC6CC))
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                text = "え - e",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                Canvas(
                    Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { offset -> currentPath.moveTo(offset.x, offset.y) },
                                onDrag      = { change, _ -> currentPath.lineTo(change.position.x, change.position.y) },
                                onDragEnd   = {
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

                    // dashed grid
                    val inset = 16.dp.toPx()

                    // dashed effect
                    val dash = PathEffect.dashPathEffect(
                        floatArrayOf(8.dp.toPx(), 8.dp.toPx()), 0f
                    )

                    // vertical center line, inset
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(cx, inset),
                        end   = Offset(cx, h - inset),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect  = dash
                    )

                    // horizontal center line, inset
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(inset, cy),
                        end   = Offset(w - inset, cy),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect  = dash
                    )

                    // draw the character "い" as text
                    drawIntoCanvas { canvas ->
                        val paint = android.graphics.Paint().apply {
                            isAntiAlias = true
                            color        = android.graphics.Color.LTGRAY
                            textSize     = h * 0.8f
                            textAlign    = android.graphics.Paint.Align.CENTER
                        }
                        canvas.nativeCanvas.drawText("え", cx, cy + paint.textSize / 3f, paint)
                    }

                    // user strokes
                    val brush = 16.dp.toPx()
                    paths.forEach { p ->
                        drawPath(
                            path  = p,
                            color = Color.Black.copy(alpha = 0.8f),
                            style = Stroke(width = brush, cap = StrokeCap.Round, join = StrokeJoin.Round)
                        )
                    }
                    drawPath(
                        path  = currentPath,
                        color = Color.Red.copy(alpha = 0.8f),
                        style = Stroke(width = brush, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play “え”",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable { playSound(context, R.raw.e) }
                )

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Clear",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            paths.clear()
                            currentPath = Path()
                        }
                )
            }

            Spacer(Modifier.height(24.dp))

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

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    BackHandler(onBack = navigateBack)
}

@Preview(showBackground = true)
@Composable
fun EHiraganaWriteScreenPreview() {
    EHiraganaWriteScreen(
        navigateBack = {},
        navigateToDashboard = {},
        navigateToHiraganaChart = {}
    )
}
