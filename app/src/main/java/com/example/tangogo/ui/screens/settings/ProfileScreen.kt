package com.example.tangogo.ui.screens.settings

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R
import com.example.tangogo.model.User
import com.example.tangogo.ui.screens.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel? = null,
    user: User = User(),
    navigateToSettings: () -> Unit = {},
    onDeleteAccount: (Context) -> Unit = {},
    navigateToEditName: () -> Unit = {},
    navigateToEditPassword: () -> Unit = {},
    navigateToWelcome: () -> Unit = {}
) {
    val context = LocalContext.current

    //val userState by viewModel?.user?.collectAsState(initial = user) ?: remember { mutableStateOf(user) }
    val userState by viewModel?.user?.collectAsState() ?: remember { mutableStateOf(user) }
    var showDialog by remember { mutableStateOf(false) }

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

            // Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = navigateToSettings,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color(0xFF3F3F3F)
                    )
                }
                Text(
                    text = "Profile",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Display updated name from user state
            val fullName = "${userState.firstName} ${userState.lastName}".trim()
            val email = userState.email ?: ""

            // ProfileField for Name - Clicking navigates to Edit Name
            ProfileField(
                label = "Name",
                value = fullName,
                enabled = false,  // Not editable, just for displaying
                onClick = navigateToEditName // Trigger navigation to Edit Name screen
            )

            // ProfileField for Password
            ProfileField(
                label = "Password",
                value = "********",
                enabled = false, // Not editable, just for display
                isPassword = true,
                onClick = navigateToEditPassword // Trigger navigation to Edit Password screen
            )

            // ProfileField for Email
            ProfileField(
                label = "Email",
                value = email,
                enabled = false  // Not editable
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Delete Account Button
            OutlinedButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text("DELETE ACCOUNT", fontWeight = FontWeight.Bold, color = Color.Red)
            }

            // Dialog for account deletion
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Are you sure?") },
                    text = { Text("This will permanently delete your account.") },
                    confirmButton = {
                        TextButton(onClick = {
                            // Pass the context and navigate to welcome screen after deletion
                            onDeleteAccount(context) // Delete the account
                            navigateToWelcome() // Navigate to welcome screen
                            showDialog = false // Close the dialog after confirming
                        }) {
                            Text("Yes", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("No")
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    containerColor = Color.White
                )
            }
        }
    }
}



@Composable
fun ProfileField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .then(
                    if (!enabled && onClick != null) Modifier.clickable { onClick() } else Modifier
                ),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFF007AFF),
                cursorColor = Color(0xFF007AFF),
                disabledTextColor = Color.Gray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        user = User(
            firstName = "Nurin",
            lastName = "Pishol",
            email = "email@example.com"
        ),
        navigateToEditName = {},
        navigateToEditPassword = {}
    )
}
