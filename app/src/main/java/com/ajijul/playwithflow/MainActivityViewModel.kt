package com.ajijul.playwithflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    val countDownFlow = flow {
        var startingValue = 10
        while (startingValue > 0) {
            delay(1000)
            emit(startingValue)
            startingValue--
        }
    }

    init {
        //collectTimeInternally()
        collectLatestTimeInternally()
    }

    private fun collectTimeInternally() {
        viewModelScope.launch {
            countDownFlow.collect {
                println("Collect value $it")
            }
        }
    }
    private fun collectLatestTimeInternally() {
        viewModelScope.launch {
            countDownFlow.collectLatest {
                delay(1500)
                println("Collect Latest Value $it")
            }
        }
    }
}