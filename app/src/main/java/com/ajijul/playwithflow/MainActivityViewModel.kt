package com.ajijul.playwithflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainActivityViewModel : ViewModel() {
    val countDownFlow = flow {
        var startingValue = 10
        while (startingValue > 0) {
            delay(1000)
            emit(startingValue)
            startingValue --
        }
    }
}