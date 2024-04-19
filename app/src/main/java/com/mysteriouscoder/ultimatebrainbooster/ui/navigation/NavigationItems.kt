package com.mysteriouscoder.ultimatebrainbooster.ui.navigation

sealed class Navigationitems(var route:String) {


    object BinauralBeats: Navigationitems("Binaural Beats")
    object SoothingMusic: Navigationitems("Soothing Music")
    object  MusicPlayerScreen: Navigationitems("MusicPlayerScreen")
    object  MusicPlayerScreen2: Navigationitems("MusicPlayerScreen2")
    object MusicPlayerWithIndex: Navigationitems("MusicPlayerWithIndex/{idx}")
    object MusicPlayerWithIndex2: Navigationitems("MusicPlayerWithIndex2/{idx}")
    object  FAQ: Navigationitems("FAQ")


}