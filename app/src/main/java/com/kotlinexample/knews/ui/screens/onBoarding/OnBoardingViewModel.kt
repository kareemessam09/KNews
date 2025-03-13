package com.kotlinexample.knews.ui.screens.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinexample.knews.domain.useCases.app_entry.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.saveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCase.saveAppEntry()
        }

    }


}