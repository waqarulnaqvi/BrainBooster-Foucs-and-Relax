package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier // Correct import
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.ads.MobileAds
import com.mysteriouscoder.ultimatebrainbooster.FAQComponent
import com.mysteriouscoder.ultimatebrainbooster.Heading
import com.mysteriouscoder.ultimatebrainbooster.data.faqbinauralbeatslist
import com.mysteriouscoder.ultimatebrainbooster.data.faqsothingmusiclist
import com.mysteriouscoder.ultimatebrainbooster.ads.BannerAds
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightOrange
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightPurple

@Composable
fun FAQ() {
        val viewModel = viewModel<FAQViewModel>()
        val searchText by viewModel.searchText.collectAsState()
        val persons by viewModel.persons.collectAsState()
        val isSearching by viewModel.isSearching.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth().padding(horizontal=20.dp).padding(top=10.dp),
                placeholder = { Text(text = "Search") },
                shape = MaterialTheme.shapes.extraLarge,
            )
//            Spacer(modifier = Modifier.height(16.dp))
            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = 10.dp)

                ) {
                    item {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading()
                        }
//                        Spacer(modifier = Modifier.height(10.dp))
                        BannerAds(modifier = Modifier.fillMaxSize())
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    items(faqbinauralbeatslist.size) { faq ->
                        FAQComponent(
                            question = faqbinauralbeatslist[faq].question,
                            answer = faqbinauralbeatslist[faq].answer,
                            color = if (faq % 2 == 0) LightPurple else LightOrange

                        )
                        Spacer(modifier = Modifier.height(20.dp))

                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading("Soothing Music FAQ")
                        }
//                        Spacer(modifier = Modifier.height(10.dp))
                        BannerAds(modifier = Modifier.fillMaxSize())
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    items(faqsothingmusiclist.size) { faq ->
                        FAQComponent(
                            question = faqsothingmusiclist[faq].question,
                            answer = faqsothingmusiclist[faq].answer,
                            color = if (faq % 2 == 0) LightPurple else LightOrange

                        )
                        Spacer(modifier = Modifier.height(20.dp))

                    }


                }
            }
        }
}

@Preview(showSystemUi = true)
@Composable
private fun FAQPreview() {
FAQ()
}
