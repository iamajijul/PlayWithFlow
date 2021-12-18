package com.ajijul.playwithflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
@FlowPreview
class MainActivityViewModel : ViewModel() {

    init {
        tryFlatMapConcat1()
    }

    private fun tryFlatMapConcat() {
        val flow1 = flow<Int> {
            emit(1)
            delay(1000)
            emit(2)
        }
        viewModelScope.launch {
            flow1.flatMapConcat {
                flow { emit(it + 1) }
            }.collect {
                println("Emitted Value - $it")
            }
        }
    }
    private fun tryFlatMapConcat1() {
        val flow1 = (1..5).asFlow()
        viewModelScope.launch {
            flow1.flatMapConcat {
                flow { emit(it + 1) }
            }.collect {
                println("Emitted Value - $it")
            }
        }
    }
}