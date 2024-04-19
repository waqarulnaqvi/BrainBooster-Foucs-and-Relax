package com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.physicalandmentalhealth.domain.usecases.AppEntryUseCases
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding.components.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
):ViewModel () {

//    onEvent is only the public function inside the viewmodel

fun  onEvent(event: OnBoardingEvent){
        when(event)
        {
            is OnBoardingEvent.SaveAppEntry ->{
              saveAppEntry()
            }

        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()

        }
    }

}

//This architecture is a combination between MVI or MVVM.