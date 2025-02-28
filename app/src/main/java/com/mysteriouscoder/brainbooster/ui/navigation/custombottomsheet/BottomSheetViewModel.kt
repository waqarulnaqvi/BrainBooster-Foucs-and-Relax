package com.mysteriouscoder.brainbooster.ui.navigation.custombottomsheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CustomBottomSheetViewModel:ViewModel() {
    companion object {
        var isSheetShown by mutableStateOf(false)
            private set
        fun onDismissSheet() {
            isSheetShown = false
        }
        fun onClick(){
            isSheetShown = true
        }
    }
}
