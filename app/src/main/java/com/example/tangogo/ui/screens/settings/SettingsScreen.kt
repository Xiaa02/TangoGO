@file:Suppress("DEPRECATION")

package com.example.tangogo.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tangogo.utils.PdfUtils

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel? = null,
    navigateToProfile: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateBack: () -> Unit
) {
    val actualViewModel = viewModel ?: hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    val backgroundColor = Color(0xFFFFFFFF)
    val linkColor = Color(0xFF8583CC)
    val textColor = Color(0xFF111111)
    val sectionTitleColor = Color(0xFF666666)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )
                )
                Text(
                    text = "BACK",
                    color = linkColor,
                    modifier = Modifier.clickable {
                        actualViewModel.onBackClick(navigateBack)
                    }
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Account",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = sectionTitleColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
            )

            SectionCard(
                items = listOf(
                    "Profile" to {
                        actualViewModel.onNavigateToProfile(navigateToProfile)
                    }
                )
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Support & About",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = sectionTitleColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
            )

            SectionCard(
                items = listOf(
                    "How to Use" to {
                        if (!isPreview) {
                            PdfUtils.openPdfFromAssets(context, "TangoGO_howTouse.pdf")
                        }
                    },
                    "About Developer" to {
                        actualViewModel.onNavigateToAbout(navigateToAbout)
                    },
                    "Terms and Conditions" to {
                        if (!isPreview) {
                            PdfUtils.openPdfFromAssets(context, "TangoGO_termsConditions.pdf")
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun SectionCard(items: List<Pair<String, () -> Unit>>) {
    val cardColor = Color(0xFFF2F2F7)
    val dividerColor = Color(0xFFE0E0E0)
    val textColor = Color.Black
    val iconColor = Color.Gray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(cardColor)
    ) {
        items.forEachIndexed { index, (label, onClick) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() }
                    .padding(vertical = 16.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = iconColor
                )
            }
            if (index != items.lastIndex) {
                Divider(color = dividerColor, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        viewModel = null,
        navigateBack = {},
        navigateToProfile = {},
        navigateToAbout = {}
    )
}
