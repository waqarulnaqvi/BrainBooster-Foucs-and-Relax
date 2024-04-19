package com.mysteriouscoder.ultimatebrainbooster.ui.screens.navigation

sealed class Navigationitems(var route:String) {


    object BinauralBeats: Navigationitems("Binaural Beats")
    object SoothingMusic: Navigationitems("Soothing Music")
    object  FAQ: Navigationitems("FAQ")


}