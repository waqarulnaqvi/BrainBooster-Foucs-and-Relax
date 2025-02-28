package com.mysteriouscoder.brainbooster.ui.screens.binauralbeats

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.brainbooster.Heading
import com.mysteriouscoder.brainbooster.NewsButton
import com.mysteriouscoder.brainbooster.OnImageCard
import com.mysteriouscoder.brainbooster.PreferenceManager
import com.mysteriouscoder.brainbooster.TotalTimeReport
import com.mysteriouscoder.brainbooster.ads.BannerAds
import com.mysteriouscoder.brainbooster.data.binauralbeatslist
import com.mysteriouscoder.brainbooster.ui.navigation.customdialog.CustomDialogViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinauralBeats(
    onCardSelect: (idx: Int) -> Unit,
    showExitDialog: MutableState<Boolean>,
//    onBackPress: () -> Unit
) {
    val context = LocalContext.current
//    val activity = remember(context) { context as? ComponentActivity }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val prefs = remember(context) { PreferenceManager(context) }
    val binauralbeats = prefs.get("binauralbeats")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Heading(
                        title = "Binaural Beats",
                        modifier = Modifier
                            .padding(end=6.dp),
                        fontSize = 22,
                    )
                },
                actions = {
                    NewsButton(onClick = {
                        showBottomSheet = true
                    })
                    Spacer(modifier = Modifier.width(10.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.scrim,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize())
        {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.scrim)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 10.dp)

            )
            {


//            item {
//
//                Row(verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    modifier = Modifier.fillMaxWidth().padding(top=10.dp, end = 10.dp))
//                {
//                    Heading(
//                        title = "Binaural Beats",
//                        modifier = Modifier
////                            .fillMaxWidth()
////                            .padding(top = 10.dp)
//                    )
//                    NewsButton(onClick = {
//                        showBottomSheet = true
//                    })
//                }
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//            }

                itemsIndexed(binauralbeatslist) { idx, task ->
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
                item {

                    Spacer(modifier = Modifier.height(38.dp))

                }

            }
            BannerAds(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

        }
        if (showBottomSheet) {

            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Heading(title="Total Time Spent:")
                    Spacer(modifier = Modifier.height(5.dp))
                    TotalTimeReport(
                        totaltime = binauralbeats?.toInt() ?: 0,
                        heading = "Binaural Beats"
                    )
                    Spacer(modifier = Modifier.height(35.dp))

//            BannerAds(modifier = Modifier
//                .fillMaxWidth())
                }

            }
        }
    }
    BackHandler(onBack = {
        //        activity?.finishAffinity()
        CustomDialogViewModel.onClick()
        if (CustomDialogViewModel.isDialogShown) {
            showExitDialog.value = true
        }
    })

}

//@Preview(showSystemUi = true)
//@Composable
//fun BinauralBeatsPreview() {
////    BinauralBeats(
////        onCardSelect = {},
//////        onBackPress = {}
////    )
//
//
//}