package com.example.tangogo.ui.screens.lessonKatakana

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatakanaL2Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        containerColor = Color(0xFFF3F0FF),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3F0FF),
                    titleContentColor = Color(0xFF3F3F3F)
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "カタカナ",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color(0xFF3F3F3F)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = navigateToNext) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_next),
                            contentDescription = "Next",
                            tint = Color(0xFF3F3F3F)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("だい1か", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("カタカナ", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Katakana", fontSize = 18.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(20.dp))

                Text("カタカナ を よみましょう", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, textAlign = TextAlign.Justify)
                Text("Let's read Katakana.", fontSize = 20.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(20.dp))
                Text("Countries.", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Replace with countries in Katakana
            CountryTextCard(R.drawable.america_img, "アメリカ", "Amerika", R.raw.amerika, context)
            CountryTextCard(R.drawable.canada_img, "カナダ", "Kanada", R.raw.canada, context)
            CountryTextCard(R.drawable.malaysia_img, "マレーシア", "Mareeshia", R.raw.malaysia, context)
            CountryTextCard(R.drawable.indonesia_img, "インドネシア", "Indoneshia", R.raw.indonesia, context)
            CountryTextCard(R.drawable.spain_img, "スペイン", "Supein", R.raw.spain, context)
            CountryTextCard(R.drawable.mexico_img, "メキシコ", "Mekishiko", R.raw.mexico, context)
            CountryTextCard(R.drawable.germany_img, "ドイツ", "Doitsu", R.raw.germany, context)
            CountryTextCard(R.drawable.france_img, "フランス", "Furansu", R.raw.france, context)
            CountryTextCard(R.drawable.italy_img, "イタリア", "Itaria", R.raw.italy, context)
            CountryTextCard(R.drawable.uk_img, "イギリス", "Igirisu", R.raw.uk, context)
            CountryTextCard(R.drawable.australia_img, "オーストラリア", "Oosutoraria", R.raw.australia, context)
            CountryTextCard(R.drawable.brazil_img, "ブラジル", "Burajiru", R.raw.brazil, context)
            CountryTextCard(R.drawable.russia_img, "ロシア", "Roshia", R.raw.russia, context)
            CountryTextCard(R.drawable.india_img, "インド", "Indo", R.raw.india, context)

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = navigateToNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF061428),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(10.dp, RoundedCornerShape(50))
            ) {
                Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CountryTextCard(
    imageResId: Int,
    countryInKatakana: String,
    countryInEnglish: String,
    soundResId: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFCFB)),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Play Sound",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { playAudio(context, soundResId) }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Country Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(countryInKatakana, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F3F3F), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(countryInEnglish, fontSize = 16.sp, color = Color(0xFF757575), textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun KatakanaL2ScreenPreview() {
    KatakanaL2Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
