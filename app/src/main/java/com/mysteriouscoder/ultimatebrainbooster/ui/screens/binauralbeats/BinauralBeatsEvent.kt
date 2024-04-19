package com.mysteriouscoder.ultimatebrainbooster.ui.screens.binauralbeats

sealed class BinauralBeatsEvent {

    data class BinauralBeatsSounds(
        val title: String,
        val subtitle: String,
        val image: Int,
    ) : BinauralBeatsEvent()
}