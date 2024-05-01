package com.mysteriouscoder.ultimatebrainbooster.ui.screens.soothingmusic

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.ultimatebrainbooster.Heading
import com.mysteriouscoder.ultimatebrainbooster.OnImageCard
import com.mysteriouscoder.ultimatebrainbooster.ads.BannerAds
import com.mysteriouscoder.ultimatebrainbooster.data.soothingmusiclist
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.GreyBg


@Composable
fun SoothingMusic(
    onCardSelect: (idx: Int) -> Unit,
//    onBackPress: () -> Unit
) {
    val context = LocalContext.current
    val activity = remember(context) { context as? ComponentActivity }
    Box(modifier =Modifier.fillMaxSize())
    {
    LazyColumn(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .background(GreyBg)
        .fillMaxSize()
        .padding(horizontal =  10.dp)

    )
    {


        item {

            Heading(
                title = "Soothing Music",
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

        }
        itemsIndexed(soothingmusiclist){ idx, task->
            OnImageCard(
                title = task.title,
                time = task.time,
                image = task.image,
                idx = idx,
            ) {
                onCardSelect(idx)
            }

            Spacer(modifier = Modifier.height(10.dp))


        }
        item{
            Spacer(modifier = Modifier.height(38.dp))

        }
    }

        BannerAds(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter))

    }
    BackHandler(onBack = {
        activity?.finishAffinity()
    })
}

@Preview(showSystemUi = true)
@Composable
fun SWPreview() {
//    SoothingMusic(NavHostController(LocalContext.current))
    SoothingMusic(onCardSelect = {},
//        onBackPress = {}
    )


}