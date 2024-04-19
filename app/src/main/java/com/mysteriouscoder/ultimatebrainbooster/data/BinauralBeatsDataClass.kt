package com.mysteriouscoder.ultimatebrainbooster.data

import com.mysteriouscoder.ultimatebrainbooster.R

data class BinauralBeatsDataClass(
    val title: String,
    val time: String,
    val image: Int
)

val binauralbeatslist = listOf(
    BinauralBeatsDataClass(
        title = "Creativity Boost",
        time = "10 min",
        image = R.drawable.creativityboost
    ),
    BinauralBeatsDataClass(
        title = "Memory Boost",
        time = "10 min",
        image = R.drawable.memoryboost
    ),
    BinauralBeatsDataClass(
        title = "Unlock hidden genius",
        time = "10 min",
        image = R.drawable.unlockhiddengenius
    ),
    BinauralBeatsDataClass(
        title = "Meditation Aid",
        time = "10 min",
        image = R.drawable.meditationaid
    ),
    BinauralBeatsDataClass(
        title = "Improved Sleep",
        time = "10 min",
        image = R.drawable.improvedsleep
    ),
    BinauralBeatsDataClass(
        title = "Enhanced Focus",
        time = "10 min",
        image = R.drawable.enhancedfocus
    ),
    BinauralBeatsDataClass(
        title = "Mood Enhancement",
        time = "10 min",
        image = R.drawable.moodenhancement
    ),
    BinauralBeatsDataClass(
        title = "Open your Third Eye",
        time = "10 min",
        image = R.drawable.openyourthirdeye
    ),
    BinauralBeatsDataClass(
        title = "Relaxation & Stress Relief",
        time = "10 min",
        image = R.drawable.relaxationandstressrelief
    ),
    BinauralBeatsDataClass(
        title = "Improved Brain Sync",
        time = "10 min",
        image = R.drawable.improvedbrainsync
    )
)
