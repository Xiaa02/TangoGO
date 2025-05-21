package com.example.tangogo.ui.screens.lessonHiragana

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tangogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hiragana101Screen(
    navigateBack: () -> Unit,
    navigateToNext: () -> Unit
) {
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
                            text = "ひらがな",
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
                Text(
                    text = "Lesson 1",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "ひらがな",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )


                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Hiragana 101",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Hiragana (ひらがな) is one of the three Japanese writing systems that consist of 46 basic symbols. It is used to write native Japanese words and grammatical elements.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF444444),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Consonants are combined with the vowels /a, i, u, e, o/ to form syllables. (*except for the letter [ん/N])",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF444444),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            HiraganaItem(
                word = "ふりがな",
                reading = "Furigana",
                explanation = "Hiragana is used to assist in reading kanji.\nUsually used for Japanese language beginners, children's books, and signage.",
                example = "Example: 桜 (さくら) — sakura [cherry blossom]"
            )

            HiraganaItem(
                word = "おくりがな",
                reading = "Okurigana",
                explanation = "Combination of Hiragana and Kanji in one word that holds grammar function.",
                example = "Example: 食べます (たべます) — tabemasu [eat/eating]"
            )

            HiraganaItem(
                word = "せいおん",
                reading = "Sei-on",
                explanation = "It refers to the basic hiragana sounds like あ, い, う, え, お, か, き, く, etc.\nThey are \"clear sounds\", without any marks like ゛(dakuten) or ゜(handakuten).",
                example ="Example:\n"+
                        "• [お/o] and [を/o] pronunciation:\n" +
                        "   - Pronunciation is the same.\n" +
                        "   - [お] is used in normal words:\n" +
                        "       * おかね (okane) — money\n" +
                        "       * おなか (onaka) — stomach\n" +
                        "   - [を] is used as a grammar particle:\n" +
                        "       * りんごをたべます (ringo-o tabemasu) — I eat an apple\n\n" +
                        "• [は/ha] and [は/wa] pronunciation:\n" +
                        "   - Pronunciation is different.\n" +
                        "   - [は] is used for [ha] sounds in words:\n" +
                        "       * はる (haru) — spring\n" +
                        "       * はこ (hako) — box\n" +
                        "   - [は] (read as 'wa') is used as a grammar particle:\n" +
                        "       * ハシムさんはいいがくせいです (Hashimu-san wa ii gakusei desu) — Hashim is a good student\n\n" +
                        "• [へ/he] and [へ/e] pronunciation:\n" +
                        "   - Pronunciation is different.\n" +
                        "   - [へ] is used for [he] sounds in words:\n" +
                        "       * へい (hei) — brick wall\n" +
                        "   - [へ] (read as 'e') is used as a grammar particle:\n" +
                        "       * こうえんへいきます (Kouen-e ikimasu) — I go to the park"
            )

            HiraganaItem(
                word = "はつおん",
                reading = "Hatsu-on",
                explanation = "It refers to the ん (N) sound, known as 撥音 (hatsuon) — the special nasal sound \"ん\" (n).",
                example = "Example:\n"+
                        "• [m] sound before 'b', 'p', 'm' sounds:\n" +
                        "   - あんぱん (ampan) — Japanese snack\n" +
                        "   - しんぶん (shimbun) — newspaper\n" +
                        "   - さんま (samma) — a type of fish\n\n" +
                        "• [ŋ] (ng) sound before 'k', 'g' sounds:\n" +
                        "   - こんかい (kongkai) — this time\n" +
                        "   - あんがい (anggai) — unexpected\n" +
                        "   - りんご (ringgo) — apple\n\n" +
                        "• [n] sound before other sounds:\n" +
                        "   - あんしん (anshin) — safe / relieved\n" +
                        "   - かんたん (kantan) — easy\n" +
                        "   - せんせい (sensei) — teacher"
            )

            HiraganaItem(
                word = "だくおん",
                reading = "Daku-on",
                explanation = "Daku-on refers to voiced sounds made by adding ゛to k, s, t, h rows.\nSounds like g, z, d, b.",
                example = "Example:\n"+
                        "• か → が (ka → ga)\n" +
                        "• さ → ざ (sa → za)\n" +
                        "• た → だ (ta → da)\n" +
                        "• は → ば (ha → ba)\n\n" +
                        "Special cases:\n" +
                        "• じ and ぢ are pronounced the same (ji)\n" +
                        "• ず and づ are pronounced the same (zu)"
            )

            HiraganaItem(
                word = "はんだくおん",
                reading = "Handaku-on",
                explanation = "Handaku-on refers to the semi-voiced sounds made by adding ゜to the 'h' row, changing it to 'p' sounds.",
                example = "Example:\n"+
                        "• は → ぱ (ha → pa)\n• ひ → ぴ (hi → pi)\n• ふ → ぷ (fu → pu)\n• へ → ぺ (he → pe)\n• ほ → ぽ (ho → po)"
            )

            HiraganaItem(
                word = "ようおん",
                reading = "You-on",
                explanation = "You-on combines smaller や (ya), ゆ (yu), or よ (yo) with other hiragana to form blended sounds.",
                example = "• き + や → きゃ (kya)\n" +
                        "• ぎ + や → ぎゃ (gya)\n\n" +
                        "Examples:\n" +
                        "• きゃく (kyaku) — guest\n" +
                        "• きやく (kiyaku) — regulation (different meaning if ya is not small!)"
            )

            HiraganaItem(
                word = "ちょうおん",
                reading = "Chou-on",
                explanation = "Chou-on is the lengthening of vowel sounds (a, i, u, e, o), making the sound longer by one beat.",
                example = "• 4 tempo: おばさん (obasan) — aunt\n" +
                        "• 5 tempo: おばあさん (obaasan) — grandmother\n\n" +
                        "Other examples:\n" +
                        "• おかあさん (okaasan) — mother\n" +
                        "• せんしゅう (senshuu) — last week\n" +
                        "• とうきょう (toukyou) — Tokyo\n" +
                        "• おおさか (oosaka) — Osaka"
            )

            HiraganaItem(
                word = "そくおん",
                reading = "Soku-on",
                explanation = "Soku-on uses a small っ (small tsu) to double the consonant that follows (pp, tt, ss, kk sounds).",
                example ="Example:\n"+
                        "• しっぽ (shippo) — tail (pp)\n" +
                        "• あさって (asatte) — day after tomorrow (tt)\n" +
                        "• まっしろ (masshiro) — pure white (ss)\n" +
                        "• さっき (sakki) — a while ago (kk)\n\n" +
                        "⚡ Important: Small っ must be written smaller to avoid different meanings.\n\n" +
                        "Small つ (っ): はっか (hakka) — ignition\n" +
                        "Big つ (つ): はつか (hatsuka) — 20th day\n" +
                        "No つ: はか (haka) — grave"
            )

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
fun HiraganaItem(
    word: String,
    reading: String,
    explanation: String,
    example: String
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDFCFB)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = word,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF3F3F3F)
                    )
                    Text(
                        text = reading,
                        fontSize = 18.sp,
                        color = Color(0xFF757575)
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = Color(0xFF757575)
                    )
                }
            }

            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = explanation,
                        fontSize = 16.sp,
                        color = Color(0xFF444444)
                    )

                    if (example.isNotBlank()) {
                        Text(
                            text = example,
                            fontSize = 16.sp,
                            color = Color(0xFF5C6BC0),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Hiragana101ScreenPreview() {
    Hiragana101Screen(
        navigateBack = {},
        navigateToNext = {}
    )
}
