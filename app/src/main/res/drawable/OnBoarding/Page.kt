package com.mysteriouscoder.physicalandmentalhealth.ui.Screens.OnBoarding

import androidx.annotation.DrawableRes
import com.mysteriouscoder.physicalandmentalhealth.R


data  class Page(
     val title:String,
     val description:String,
     @DrawableRes val image:Int
 )

val pages= listOf(

    Page(
        title = "Meditation",
        description="Meditation quiets the mind, reduces stress, enhances focus, fosters clarity, promotes emotional balance, cultivates self-awareness, and improves overall well-being.",
        image = R.drawable.meditationmainscreen
    ),

    Page(
        title = "Yoga & Exercises",
        description="Yoga and exercises calm the mind, reducing stress. They boost mood, improve focus, and enhance self-esteem, fostering mental resilience and positivity.",
        image = R.drawable.exercisesmainscreen
    ),

    Page(
        title = "Mind Relaxing Music",
        description="Mind relaxing music soothes nerves, easing stress, promoting calmness, enhancing focus, inducing sleep, elevating mood, and reducing anxiety for improved mental well-being.",
        image = R.drawable.soothingmusicmainscreen
    ),
    Page(
        title = "AI Friend",
        description="Interacting with AI can alleviate loneliness but excessive reliance may lead to detachment, dependency, and anxiety, affecting mental well-being negatively.",
        image = R.drawable.aimainscreen
    ),
    Page(
        title = "Progress Tracking",
        description="Progress tracking can alleviate anxiety by providing a sense of achievement. It can motivate and boost confidence, reducing stress levels.",
        image = R.drawable.progresstrackingphonewallpaper
    )

)