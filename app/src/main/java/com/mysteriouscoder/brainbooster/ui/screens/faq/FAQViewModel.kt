package com.mysteriouscoder.brainbooster.ui.screens.faq

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.brainbooster.data.binauralbeatsansoothingmusicfaqlist
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FAQViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
    private val _faqList = MutableStateFlow(binauralbeatsansoothingmusicfaqlist)

    @OptIn(FlowPreview::class)
    val faqList = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_faqList) { text, faqList ->
            if (text.isBlank()) {
                Log.d("Tag", "${faqList.size}")
                faqList
            } else {
                delay(1000L)
                val filterIndexed = faqList.filter{
                    it.matches(text.trim())
                }
                Log.d("Tag", filterIndexed.toString())
                filterIndexed
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _faqList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
