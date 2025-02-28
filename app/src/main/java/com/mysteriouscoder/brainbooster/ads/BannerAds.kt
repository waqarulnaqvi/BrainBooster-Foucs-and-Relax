package com.mysteriouscoder.brainbooster.ads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun BannerAds(modifier:Modifier,adId:String="ca-app-pub-4448937870984996/5745138234")
{

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(24.dp))
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = adId
                    loadAd(AdRequest.Builder().build())
                    adListener = object : AdListener() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
//                                Log.d("AdMob", "Ad failed to load: ${adError.message}")
                        }

                        override fun onAdLoaded() {
//                                Log.d("AdMob", "Ad loaded successfully")
                        }
                    }
                }
            }
        )
    }
//    Column(
//        modifier = modifier
//    ) {
//        Spacer(modifier = Modifier.size(24.dp))
//        AndroidView(modifier = Modifier.fillMaxWidth()
//            ,factory ={
//             AdView(it).apply {
//                      setAdSize(AdSize.BANNER)
//                      //Request An Ad
//                       adUnitId = adId
//                      loadAd(AdRequest.Builder().build())
//                 }
//            } )
//    }
}