package com.mysteriouscoder.brainbooster.ui.navigation

sealed class Navigationitems(var route:String) {


    data object BinauralBeats: Navigationitems("Binaural Beats")
    data object SoothingMusic: Navigationitems("Soothing Music")
    data object  MusicPlayerScreen: Navigationitems("MusicPlayerScreen")
    data object  MusicPlayerScreen2: Navigationitems("MusicPlayerScreen2")
    data object MusicPlayerWithIndex: Navigationitems("MusicPlayerWithIndex/{idx}")
    data object MusicPlayerWithIndex2: Navigationitems("MusicPlayerWithIndex2/{idx}")
    data object  FAQ: Navigationitems("FAQ")


}