package com.mysteriouscoder.ultimatebrainbooster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.ads.MobileAds
import com.mysteriouscoder.ultimatebrainbooster.ui.navigation.BottomNavigation
import com.mysteriouscoder.ultimatebrainbooster.ui.navigation.customdialog.CustomDialogViewModel
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


                    BottomNavigation(viewModel)
                }
            }
        }
    }
}

