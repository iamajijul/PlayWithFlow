package com.ajijul.playwithflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    val countDownFlow = flow {
        var startingValue = 5
        while (startingValue > 0) {
            delay(1000)
            emit(startingValue)
            startingValue--
        }
    }

    init {
        collectTimeInternally()
    }

    private fun collectTimeInternally() {
        viewModelScope.launch {
            val reduceResult = countDownFlow
                .reduce { accumulator, value ->
                    println("accumulator is $accumulator  + $value")
                    accumulator + value
                }
            println("Reduce Result $reduceResult")

            val foldResult = countDownFlow
                .fold(100) { accumulator, value ->
                    println("accumulator is $accumulator  -  $value")
                    accumulator - value
                }
            println("Fold Result $foldResult")
        }
    }
}