package com.mysteriouscoder.ultimatebrainbooster

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.MobileAds
import com.mysteriouscoder.ultimatebrainbooster.ui.navigation.customdialog.CustomDialogViewModel
import com.mysteriouscoder.ultimatebrainbooster.ui.navigation.BottomNavigation
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.Purple80
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.UltimateBrainBoosterTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<CustomDialogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen()//if you forget to mention this then you will get by default action bar in your app which is not good and it will not look good
        MobileAds.initialize(this)
//        supportActionBar?.hide()
        setContent {
            UltimateBrainBoosterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    Box(modifier = Modifier.fillMaxSize()) {
//                        ActionBarWithOptionsMenu()
//                    }
                    BottomNavigation(viewModel)
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ActionBarWithOptionsMenu() {
//    var showMenu by remember { mutableStateOf(false) }
//    val context = LocalContext.current
//
//    TopAppBar(
//        title = { Text("My AppBar", color = MaterialTheme.colorScheme.onPrimary) },
//        colorScheme = MaterialTheme.colorScheme.copy(
//            primary = Purple80,
//            onPrimary = Color.White
//        ),
//        actions = {
//            IconButton(onClick = { showMenu = !showMenu }) {
//                Icon(Icons.Default.MoreVert, contentDescription = "More options", tint = MaterialTheme.colorScheme.onPrimary)
//            }
//
//            DropdownMenu(
//                expanded = showMenu,
//                onDismissRequest = { showMenu = false }
//            ) {
//                // Dropdown menu items go here
//            }
//        }
//    )
//}