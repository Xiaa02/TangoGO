package com.example.tangogo.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tangogo.R
import com.example.tangogo.ui.theme.TangoGOTheme

@Composable
fun WelcomeScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel(),
) {
    Surface {
        WelcomeScreenContent(
            onGetStarted = { viewModel.onGetStarted(openAndPopUp) }
        )
    }
}

@Composable
fun WelcomeScreenContent(
    modifier: Modifier = Modifier,
    onGetStarted: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Circle with "TangoGO" and "A1 Level"
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .shadow(8.dp, shape = CircleShape)
                    .background(color = Color(0xFFECEAFF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "TangoGO",
                        fontSize = 55.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            brush = Brush.linearGradient(
                                listOf(Color(0xFF9DCEFF), Color(0xFFCC8FED))
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Starter A1 Level",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(R.color.black)
                    )

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF061428),
                    contentColor   = Color.White,
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text(
                    text = "Start",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TangoGOTheme {
        WelcomeScreenContent(
            onGetStarted = {},
            modifier = Modifier
        )
    }
}
