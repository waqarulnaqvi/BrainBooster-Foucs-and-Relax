package com.mysteriouscoder.ultimatebrainbooster.ads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAds(modifier:Modifier,adId:String="ca-app-pub-3940256099942544/6300978111")
{
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        AndroidView(modifier = Modifier.fillMaxWidth()
            ,factory ={
             AdView(it).apply {
                      setAdSize(AdSize.FULL_BANNER)
                      //Request An Ad
                       adUnitId = adId
                      loadAd(AdRequest.Builder().build())
                 }
            } )

    }

    
}