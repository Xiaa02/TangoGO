package com.example.tangogo.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@Composable
fun EditNameScreen(
    currentName: String,
    onSave: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit
) {
    // Split currentName into first and last names. Handle cases where there might be only one name.
    val (initialFirst, initialLast) = currentName.split(" ").let {
        val firstName = it.getOrNull(0).orEmpty()
        val lastName = it.drop(1).joinToString(" ") // Join the rest of the parts as the last name
        firstName to lastName
    }

    var firstName by remember { mutableStateOf(initialFirst) }
    var lastName by remember { mutableStateOf(initialLast) }

    val fullName = "${firstName.trim()} ${lastName.trim()}".trim()
    val isValid = fullName.isNotBlank() && fullName != currentName

    // Used for triggering navigation after save
    var isSaved by remember { mutableStateOf(false) }

    // Ensure navigation happens after the save
    LaunchedEffect(isSaved) {
        if (isSaved) {
            // Navigate to profile screen after saving
            navigateToSettings()
        }
    }

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

            // Top Bar with Back Navigation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color(0xFF3F3F3F)
                    )
                }

                Text(
                    text = "Edit Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // First Name Field
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Last Name Field
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Save Button
            Button(
                onClick = {
                    onSave(fullName)  // Pass the updated full name to the ViewModel
                    isSaved = true     // Trigger navigation after saving
                },
                enabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("SAVE")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EditNameScreenPreview() {
    EditNameScreen(
        currentName = "Nurin Pishol",
        onSave = {},
        navigateBack = {},
        navigateToSettings = {}
    )
}
