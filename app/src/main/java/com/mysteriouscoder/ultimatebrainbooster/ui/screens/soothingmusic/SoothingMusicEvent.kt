package com.mysteriouscoder.ultimatebrainbooster.ui.screens.soothingmusic

sealed class SoothingMusicEvent {
    data class SoothingMusic(
        val title: String,
        val subtitle: String,
        val image: Int,
    ) : SoothingMusicEvent()
}