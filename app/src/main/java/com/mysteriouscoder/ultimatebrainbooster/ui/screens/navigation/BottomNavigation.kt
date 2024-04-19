package com.mysteriouscoder.ultimatebrainbooster.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mysteriouscoder.ultimatebrainbooster.R
import com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq.FAQ
import com.mysteriouscoder.ultimatebrainbooster.ui.screens.binauralbeats.BinauralBeats
import com.mysteriouscoder.ultimatebrainbooster.ui.screens.soothingmusic.SoothingMusic
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.Purple80
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.White

@Composable
fun BottomNavigation() {

//    var selectedScreen by remember {
//        mutableStateOf(1)
//    }
    val navigationController = rememberNavController()


//    val screens = listOf("Binaural Beats", "Soothing music", "FAQ")


    val items = listOf(
        BottomNavigationItem(
            title = "Binaural Beats",
            icon = painterResource(id = R.drawable.binauralbeatsicon),

            ),

        BottomNavigationItem(
            title = "Soothing Music",
            icon = painterResource(id = R.drawable.soothingmusicicon),


            ),

        BottomNavigationItem(
            title = "FAQ",
            icon = painterResource(id = R.drawable.faqicon),


            )


    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(


        bottomBar = {

            NavigationBar(
                modifier = Modifier.height(140.dp),
//                containerColor = Purple80,
                containerColor = White,

//                contentColor = Color.Yellow

            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                        selectedItemIndex = index
                        navigationController.navigate(item.title)

                    }, label = {
                        Text(
                            text = item.title,
                            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                            fontSize = if (selectedItemIndex == index) 10.sp else 8.sp,
                            color = if (selectedItemIndex == index) Purple80 else Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = if (selectedItemIndex == index) Modifier.padding(top = 4.dp) else Modifier.padding(
                                top = 0.dp
                            )
                        )
                    }, icon = {

                        if (index == selectedItemIndex) {

                            Column {

                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(
                                            Purple80
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = item.icon,
                                        contentDescription = item.title,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.White

                                    )
                                }
//    Spacer(modifier = Modifier.height(10.dp))
                            }
                        } else {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(28.dp),
                                tint = Color.Black

                            )
                        }
                    })
                }
            }
        }) { paddingValues ->

//        var player: MediaPlayer? = null
        NavHost(
            navController = navigationController,
            startDestination = Navigationitems.BinauralBeats.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Navigationitems.FAQ.route) {
                FAQ()
            }
            composable(Navigationitems.BinauralBeats.route) {
                BinauralBeats()

            }
            composable(Navigationitems.SoothingMusic.route) {
                SoothingMusic()
            }
        }


    }


}

data class BottomNavigationItem(
    val title: String,
    val icon: Painter,
)


@Preview
@Composable
private fun BottomNavigationPreview() {
    BottomNavigation()

}