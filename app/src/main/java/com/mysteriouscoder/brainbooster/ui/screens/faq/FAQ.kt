package com.mysteriouscoder.brainbooster.ui.screens.faq

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mysteriouscoder.brainbooster.FAQComponent
import com.mysteriouscoder.brainbooster.Heading
import com.mysteriouscoder.brainbooster.ui.navigation.customdialog.CustomDialogViewModel

@Composable
fun FAQ(
    showExitDialog: MutableState<Boolean>,
) {
    val viewModel = viewModel<FAQViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val faqList by viewModel.faqList.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.scrim)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            shape = MaterialTheme.shapes.large,
            label={ Text(text = "Search by Question")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Heading(
                title = "Binaural Beats & Soothing Music FAQ",
                fontSize = 16, textAlign = TextAlign.Center,
                modifier = Modifier.padding(end = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

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
                    .background(MaterialTheme.colorScheme.scrim)
//                    .padding(top = 10.dp)

            ) {
                item {
                    if (searchText.isNotEmpty()) {

                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading(
                                title = "Search Results (${faqList.size})",
                                fontSize = 20, textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                items(faqList) {  faq ->
                    FAQComponent(
                        question = faq.question,
                        answer = faq.answer,
                        color =faq.color
//                        if (isSystemInDarkTheme()) {
//                            if (idx % 2 == 0) LightBlue else LightGreen
//                        } else {
//                            if (idx % 2 == 0) LightPurple else LightOrange
//                        }
//                        }

//                        if (idx % 2 == 0) {
//                            BabyBlue
//                        } else {
//                            RedOrange
//                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
    BackHandler(onBack = {
        CustomDialogViewModel.onClick()
        if (CustomDialogViewModel.isDialogShown) {
            showExitDialog.value = true
        }
    })
}
