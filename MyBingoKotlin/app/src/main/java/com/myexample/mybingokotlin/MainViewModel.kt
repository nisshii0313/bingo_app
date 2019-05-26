package com.myexample.mybingokotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    private val random = Random()
    private val histories = mutableListOf<Int>()
    private val pickNumbers = mutableListOf<Int>()

    private val state = MutableLiveData<State>()

    init {
        for (i in 0..75) {
            pickNumbers.add(i)
        }
    }

    fun pickNextNumber() {
        val index = random.nextInt(pickNumbers.size)
        val nextNumber = pickNumbers.removeAt(index)

        histories.add(nextNumber)
        state.value = State(nextNumber, createHistoryText(), isAllPicked())
    }

    internal fun getState(): LiveData<State> {
        return state
    }

    private fun createHistoryText(): String {
        return histories.joinToString(separator = ", ")
    }

    private fun isAllPicked(): Boolean {
        return pickNumbers.isEmpty()
    }

    data class State(
        val nextNumber: Int,
        val historyText: String,
        val isAllPicked: Boolean
    )
}