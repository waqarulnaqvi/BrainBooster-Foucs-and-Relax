package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mysteriouscoder.ultimatebrainbooster.FAQComponent
import com.mysteriouscoder.ultimatebrainbooster.Heading
import com.mysteriouscoder.ultimatebrainbooster.data.binauralbeatsansoothingmusicfaqlist
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightBlue
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightGreen
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightOrange
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightPurple

@Composable
fun FAQ() {
    val viewModel = viewModel<FAQViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val faqList by viewModel.faqList.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
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
//                            .background(MaterialTheme.colorScheme.secondary)
                    .padding(top = 10.dp)

            ) {
                item {
                    if (searchText.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading("Search Results")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading("Binaural Beats FAQ")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                items(faqList.size) { faq ->
                    if (faq == 64) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading("Soothing Music FAQ")
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    FAQComponent(
                        question = binauralbeatsansoothingmusicfaqlist[faq].question,
                        answer = binauralbeatsansoothingmusicfaqlist[faq].answer,
                        color = if (isSystemInDarkTheme()) {
                            if (faq % 2 == 0) LightBlue else LightGreen
                        } else {
                            if (faq % 2 == 0) LightPurple else LightOrange
                        }

                    )
                    Spacer(modifier = Modifier.height(20.dp))

                }
//                        item {
//                            Spacer(modifier = Modifier.height(20.dp))
//                            Row {
//                                Spacer(modifier = Modifier.width(20.dp))
//                                Heading("Soothing Music FAQ")
//                            }
//                            Spacer(modifier = Modifier.height(20.dp))
//                        }
//                        items(faqsothingmusiclist.size) { faq ->
//                            FAQComponent(
//                                question = faqsothingmusiclist[faq].question,
//                                answer = faqsothingmusiclist[faq].answer,
//                                color = if (faq % 2 == 0) LightPurple else LightOrange
//
//                            )
//                            Spacer(modifier = Modifier.height(20.dp))
//
//                        }
//                        item{
//                            Spacer(modifier = Modifier.height(10.dp))
//                        }


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FAQPreview() {
    FAQ()
}
