package com.mysteriouscoder.ultimatebrainbooster

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.ads.MobileAds
import com.mysteriouscoder.ultimatebrainbooster.ads.BannerAds
import com.mysteriouscoder.ultimatebrainbooster.ui.screens.navigation.BottomNavigation
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.UltimateBrainBoosterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MobileAds.initialize(this)

        setContent {
            UltimateBrainBoosterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    OptionMenu()

                BottomNavigation()


//                    BannerAds(modifier = Modifier.fillMaxWidth() )
//
//                    Text(text = "Hello, World")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionMenu() {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = { Text("Brain Booster") },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Default.Favorite, "")
            }

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
//                DropdownMenuItem(onClick = {
//                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
//                    showMenu = false
//                }) {
//                    Text(text = "Settings")
//                }
//
//                // Uncomment the following lines if you want to add a "Logout" option
//                DropdownMenuItem(onClick = {
//                    Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
//                    showMenu = false
//                }) {
//                    Text(text = "Logout")
//                }

            }
        }
    )
}