package com.mysteriouscoder.brainbooster.ui.navigation.custombottomsheet.bottomsheet


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysteriouscoder.brainbooster.Heading
import com.mysteriouscoder.brainbooster.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreAppsBottomSheet(
    onDismiss: () -> Unit,
    context: Context,
    showMoreAppsSheet: Boolean
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Enable partial expansion
    )

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },

        sheetState = sheetState
    ) {
        if (showMoreAppsSheet)
        {
            Column{
                Heading(
                    title = "More Apps",
                    textAlign = TextAlign.Center,
                    fontSize = 22,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))

                ReusableMoreAppsCarousel(
                    context = context,
                    showMoreAppsSheet = showMoreAppsSheet
                )

                Spacer(modifier = Modifier.height(50.dp))

            }
        }
    }
}

@Composable
fun ReusableMoreAppsCarousel(
    context: Context,
    showMoreAppsSheet: Boolean
) {
    val titles = listOf(
        "Brain Booster: Focus & Relax",
        "Classic Winged Bird",
        "Hindi Shayari Hub",
        "Mazedar Hindi Jokes",
        "NoteMaster: Offline Organizer",
    )

    val images = listOf(
        R.drawable.ic_brain_booster_icon,
        R.drawable.ic_winged_bird_icon,
        R.drawable.ic_hindi_shayari_hub_icon,
        R.drawable.ic_mazedar_hindi_jokes_icon,
        R.drawable.ic_notemaster_icon,
    )

    val packages = listOf(
        "com.mysteriouscoder.brainbooster",
        "com.mysteriouscoder.classicwingedbird",
        "com.mysteriouscoder.hindishayarihub",
        "com.mysteriouscoder.mazedarhindijokes",
        "com.mysteriouscoder.notemaster",
    )

    val subtitles = listOf(
        "Brain Booster is an audio app with binaural beats and calming music to improve focus, clarity, and relaxation. Ideal for meditation, studying, or stress relief, it offers customizable playlists and a helpful FAQ section.",
        "Classic Winged Bird is a fun and addictive game featuring classic Flappy Bird gameplay. Navigate through obstacles while unlocking various bird characters and dynamic backgrounds. Enjoy easy tap controls and a high-score challenge.",
        "Explore the best Hindi Shayari with Hindi Shayari Hub! Discover Shayari for love, friendship, sadness, and more. Easily share your favorite Shayari with friends. Enjoy a simple design and growing categories.",
        "Mazedar Hindi Jokes is a fun app packed with hilarious Hindi jokes to brighten your day. From witty one-liners to laugh-out-loud stories, enjoy endless entertainment. Easily share jokes with friends and family.",
        "NoteMaster: Offline Organizer is your go-to app for organizing notes, tasks, and ideas without needing an internet connection. Perfect for managing to-do lists, reminders, and important thoughts. Stay organized anytime, anywhere.",
    )

    val colors = listOf(
        listOf(Color(0xFF0F61A4), Color(0xFF2491C3), Color(0xFF0591E2)),
        listOf(Color(0xFF0F61A4), Color(0xFF2491C3), Color(0xFF0591E2)),
        listOf(Color(0xFFB57EDC), Color(0xFFB747CB), Color(0xFF733093)),
        listOf(Color(0xFFC5823C), Color(0xFFCC935F), Color(0xFFC5823C)),
        listOf(Color(0xFFC5823C), Color(0xFFCC935F), Color(0xFFC5823C)),
    )

    val virtualItems = remember { List(3 * titles.size) { it % titles.size } }
    val carouselState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll logic: Start only when the sheet is open (showMoreAppsSheet is true)
    LaunchedEffect(showMoreAppsSheet) {
        if (showMoreAppsSheet) {
            while (showMoreAppsSheet) {
                delay(2400)  // Auto-scroll delay
                coroutineScope.launch {
                    val nextIndex = (carouselState.firstVisibleItemIndex + 1) % (virtualItems.size)
                    carouselState.animateScrollToItem(nextIndex)
                }
            }
        }
    }

    // Looping logic to maintain smooth scrolling
    LaunchedEffect(carouselState) {
        snapshotFlow { carouselState.firstVisibleItemIndex }.collect { index ->
            if (index == 0) {
                coroutineScope.launch {
                    carouselState.scrollToItem(titles.size) // Go to the middle of the virtual list
                }
            } else if (index == virtualItems.size - titles.size) {
                coroutineScope.launch {
                    carouselState.scrollToItem(titles.size) // Go to the middle to avoid visual jumping
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = carouselState,
        ) {
            items(virtualItems) { virtualIndex ->
                val index = virtualIndex % titles.size
                AppCarouselItem(
                    title = titles[index],
                    subtitle = subtitles[index],
                    imageRes = images[index],
                    colors = colors[index],
                    packages = packages[index],
                    context = context
                )
            }
        }

        Spacer(modifier = Modifier.height(45.dp))

        // Page Indicator
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            titles.forEachIndexed { index, _ ->
                val isActive = (carouselState.firstVisibleItemIndex % titles.size) == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(
                            if (isActive) MaterialTheme.colorScheme.primary
                            else if (isSystemInDarkTheme()) Color.White else Color.Gray
                        )
                )
            }
        }
    }
}



@Composable
fun AppCarouselItem(
    title: String,
    subtitle: String,
    imageRes: Int,
    colors: List<Color>,
    packages: String,
    context: Context,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(340.dp) // Set width as needed
            .padding(start = 20.dp)
    ) {

        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.width(330.dp)
                .offset(y=25.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(colors))
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Spacer(modifier = Modifier.height(45.dp))

                    Text(
                        text = title.uppercase(),
                        fontSize = 17.sp,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "(Available on the Google Play Store, Amazon App Store, and Oppo App Store)",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                            .height(120.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    ElevatedButton(
                        onClick = {
                            val appPackageName = packages
                            try {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=$appPackageName")
                                    )
                                )
                            } catch (e: ActivityNotFoundException) {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                                    )
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34A4A4))
                    ) {
                        Text(
                            text = "Download Now".uppercase(),
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                }

            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-10).dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}






//import android.content.ActivityNotFoundException
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.mysteriouscoder.brainbooster.BgWhiteMoreAppIcons
//import com.mysteriouscoder.brainbooster.Heading
//import com.mysteriouscoder.brainbooster.MoreAppIcons
//import com.mysteriouscoder.brainbooster.R
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MoreAppsBottomSheet(
//    onDismiss: () -> Unit,
//    context: Context,
//
//    ) {
//    ModalBottomSheet(
//        onDismissRequest = {
//            onDismiss()
//        },
////            sheetState = sheetState
//    ) {
//        Column(modifier = Modifier
//            .padding(horizontal = 20.dp)
//        ) {
//            Heading(
//                title = "More Apps",
//                textAlign = TextAlign.Center,
//                fontSize = 22,
//                modifier = Modifier.fillMaxWidth(),
//                color = MaterialTheme.colorScheme.primary
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        val appPackageName = context.packageName
//                        try {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=$appPackageName")
//                                )
//                            )
//                        } catch (e: ActivityNotFoundException) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
//                                )
//                            )
//                        }
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Spacer(modifier = Modifier.width(15.dp))
//                MoreAppIcons(
//                    image = R.drawable.ic_brain_booster_icon,
//                )
//                Spacer(modifier = Modifier.width(15.dp))
//                Heading(
//                    title = "Brain Booster",
//                    color= MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        val appPackageName = "com.mysteriouscoder.classicwingedbird"
//
//                        try {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=$appPackageName")
//                                )
//                            )
//                        } catch (e: ActivityNotFoundException) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
//                                )
//                            )
//                        }
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Spacer(modifier = Modifier.width(15.dp))
//                BgWhiteMoreAppIcons()
//                Spacer(modifier = Modifier.width(15.dp))
//                Heading(
//                    title="Classic Winged Bird",
//                    color= MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        val appPackageName = "com.mysteriouscoder.hindishayarihub"
//
//                        try {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=$appPackageName")
//                                )
//                            )
//                        } catch (e: ActivityNotFoundException) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
//                                )
//                            )
//                        }
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Spacer(modifier = Modifier.width(15.dp))
//                BgWhiteMoreAppIcons(
//                    image = R.drawable.ic_hindi_shayari_hub_icon
//                )
//                Spacer(modifier = Modifier.width(15.dp))
//                Heading(
//                    title = "Hindi Shayari Hub",
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        val appPackageName = "com.mysteriouscoder.mazedarhindijokes"
//
//                        try {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=$appPackageName")
//                                )
//                            )
//                        } catch (e: ActivityNotFoundException) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
//                                )
//                            )
//                        }
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Spacer(modifier = Modifier.width(15.dp))
//                BgWhiteMoreAppIcons(
//                    image = R.drawable.ic_mazedar_hindi_jokes_icon
//                )
//                Spacer(modifier = Modifier.width(15.dp))
//                Heading(
//                    title = "Mazedar Hindi Jokes",
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        val appPackageName = "com.mysteriouscoder.notemaster"
//
//                        try {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("market://details?id=$appPackageName")
//                                )
//                            )
//                        } catch (e: ActivityNotFoundException) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
//                                )
//                            )
//                        }
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Spacer(modifier = Modifier.width(15.dp))
//                BgWhiteMoreAppIcons(
//                    image = R.drawable.ic_notemaster_icon,
//                    border = false
//                )
//                Spacer(modifier = Modifier.width(15.dp))
//                Heading(
//                    title = "NoteMaster",
//                    color = MaterialTheme.colorScheme.primary,
//                )
//            }
//
//            Spacer(modifier = Modifier.height(45.dp))
//
//        }
//
//    }
//}
