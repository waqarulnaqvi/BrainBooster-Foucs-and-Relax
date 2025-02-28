package com.mysteriouscoder.brainbooster.data

import androidx.annotation.DrawableRes
import com.mysteriouscoder.brainbooster.R

data  class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)

val pages= listOf(

    Page(
        title = "Soothing Music",
        description="Soothing music, with its gentle melodies and tranquil rhythms, calms the mind, alleviates stress, enhances relaxation, and promotes peaceful, restorative sleep.",
        image = R.drawable.ic_soothingmusicmainwallpaper
    ),
    Page(
        title = "Binaural Beats",
        description="Binaural beats are auditory illusions created by playing slightly different frequencies in each ear, potentially promoting relaxation, focus, and altered states of consciousness.",
        image = R.drawable.ic_binauralbeatsmainwallpaper
    ),
    Page(
        title = "FAQ",
        description="It contains information about binaural beats and soothing music. This screen is designed to be user-friendly and informative, helping users understand the benefits and usage of these auditory tools.",
        image = R.drawable.ic_faqmainwallpaper
    )

)