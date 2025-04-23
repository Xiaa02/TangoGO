package com.example.tangogo.ui.screens.memory

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.asAndroidPath
import com.example.tangogo.R
import android.graphics.RectF

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AHiraganaWriteScreen(
    navigateBack: () -> Unit,
    navigateToDashboard: () -> Unit,
    onMnemonicClick: () -> Unit = {},
    onStrokeClick: () -> Unit = {},
    onWriteClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var strokeStep by remember { mutableIntStateOf(0) }

    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf(Path()) }

    val strokeImages = listOf(
        R.drawable.stroke_0,
        R.drawable.stroke_1,
        R.drawable.stroke_2,
        R.drawable.stroke_3
    )

    val correctZones = listOf(
        RectF(50f, 50f, 250f, 150f),
        RectF(80f, 100f, 220f, 200f),
        RectF(60f, 150f, 240f, 250f),
        RectF(100f, 50f, 200f, 250f)
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
                    containerColor = Color(0xFFFFC6CC),
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
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
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFC6CC))
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
                text = "あ - a",
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
                Image(
                    painter = painterResource(id = strokeImages[strokeStep]),
                    contentDescription = "Stroke ${strokeStep + 1}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )

                Canvas(modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(strokeStep) {
                        detectDragGestures(
                            onDragStart = {
                                currentPath.moveTo(it.x, it.y)
                            },
                            onDrag = { change, _ ->
                                currentPath.lineTo(change.position.x, change.position.y)
                            },
                            onDragEnd = {
                                paths.add(currentPath)

                                val bounds = RectF()
                                currentPath.asAndroidPath().computeBounds(bounds, true)
                                val targetZone = correctZones.getOrNull(strokeStep) ?: RectF(0f, 0f, 300f, 300f)

                                val overlapWidth = bounds.intersectedWidthWith(targetZone)
                                val overlapHeight = bounds.intersectedHeightWith(targetZone)
                                val overlapArea = overlapWidth * overlapHeight
                                val targetArea = targetZone.width() * targetZone.height()
                                val coverage = overlapArea / targetArea

                                if (coverage >= 0.5f) {
                                    Toast.makeText(context, "Correct stroke!", Toast.LENGTH_SHORT).show()
                                    playSound4(context, R.raw.ding)
                                }

                                currentPath = Path()
                            }
                        )
                    }
                ) {
                    paths.forEach { drawPath(it, color = Color.Black.copy(alpha = 0.8f), style = Stroke(width = 8f)) }
                    drawPath(currentPath, color = Color.Red.copy(alpha = 0.8f), style = Stroke(width = 8f))
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            playSound4(context, R.raw.a)
                        }
                )

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Clear Strokes",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable {
                            paths.clear()
                            currentPath = Path()
                        },
                    tint = Color.Black
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

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    BackHandler(onBack = navigateBack)
}

fun playSound4(context: Context, resId: Int) {
    val player = MediaPlayer.create(context, resId)
    player.setOnCompletionListener { it.release() }
    player.start()
}

@Composable
fun ButtonWithIcon(text: String, icon: Painter, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text, fontSize = 12.sp, color = Color.Black)
    }
}

fun RectF.intersectedWidthWith(other: RectF): Float {
    return maxOf(0f, minOf(this.right, other.right) - maxOf(this.left, other.left))
}

fun RectF.intersectedHeightWith(other: RectF): Float {
    return maxOf(0f, minOf(this.bottom, other.bottom) - maxOf(this.top, other.top))
}

@Preview(showBackground = true)
@Composable
fun AHiraganaWriteScreenPreview() {
    AHiraganaWriteScreen(
        navigateBack = {},
        navigateToDashboard = {},
        onMnemonicClick = {},
        onStrokeClick = {},
        onWriteClick = {}
    )
}