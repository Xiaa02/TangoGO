package com.example.tangogo.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
//import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tangogo.common.composable.EmailField
import com.example.tangogo.common.composable.PasswordField
import com.example.tangogo.ui.theme.TangoGOTheme

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = { viewModel.onLoginClick(openAndPopUp) },
        onNotRegistered = { viewModel.onNotRegistered(openAndPopUp) }
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNotRegistered: () -> Unit,
) {
    val accent = Color(0xFF8583CC)

    val waveShape = GenericShape { size, _ ->
        moveTo(0f, size.height * 0.85f)
        cubicTo(
            size.width * 0.25f, size.height * 0.55f,
            size.width * 0.35f, size.height,
            size.width,         size.height * 1.00f
        )
        lineTo(size.width, 0f)
        lineTo(0f, 0f)
        close()
    }


    Box(modifier = modifier.fillMaxSize()) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(accent, shape = waveShape)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(100.dp))

            Text(
                text = "Log in",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp, top = 170.dp, bottom = 8.dp)
            )
            Spacer(Modifier.height(1.dp))
            Box(
                Modifier
                    .align(Alignment.Start)
                    .offset(x = 12.dp)
                    .width(60.dp)
                    .height(3.dp)
                    .background(accent)
            )
            Spacer(Modifier.height(10.dp))

            EmailField(
                value = uiState.email,
                onNewValue = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 15.dp)

            )

            PasswordField(
                value = uiState.password,
                onNewValue = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )


            Spacer(Modifier.height(150.dp))

            Button(
                onClick = onLoginClick,
                colors = buttonColors(
                    containerColor = Color(0xFF061428),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(8.dp, RoundedCornerShape(30.dp))
            ) {
                Text("Login", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Text("Don't have an account? ")
                Text(
                    "Sign up",
                    color = accent,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onNotRegistered)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState(email = "", password = "")
    TangoGOTheme {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onNotRegistered = {}
        )
    }
}
