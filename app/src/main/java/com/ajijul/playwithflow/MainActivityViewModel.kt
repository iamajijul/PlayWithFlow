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
        tryFlatMapLatest()
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
                println("Emitted Value on flatMapConcat- $it")
            }
        }
    }
    private fun tryFlatMapConcat1() {
        val flow1 = (1..5).asFlow()
        viewModelScope.launch {
            flow1.flatMapConcat {
                flow { emit(it + 1) }
            }.collect {
                println("Emitted Value on tryFlatMapConcat1 - $it")
            }
        }
    }
    private fun tryFlatMapMerge() {
        val flow1 = (1..5).asFlow()
        viewModelScope.launch {
            flow1.flatMapMerge {
                flow { emit(it + 1) }
            }.collect {
                println("Emitted Value on tryFlatMapMerge - $it")
            }
        }
    }
    private fun tryFlatMapLatest() {
        val flow1 = (1..5).asFlow()
        viewModelScope.launch {
            flow1.flatMapLatest {
                flow {
                    delay(1000)
                    emit(it + 1) }
            }.collect {
                println("Emitted Value on flatMapLatest - $it")
            }
        }
    }
}