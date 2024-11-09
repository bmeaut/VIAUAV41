package statein

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun manual() {
    val flow = flowOf(0, 1, 2, 3, 4, 5)
    runBlocking {
        val stateFlow = MutableStateFlow<Int>(0)

        launch {
            stateFlow.collect { value -> println("A: $value") }
        }
        launch {
            stateFlow.collect { value -> println("B: $value") }
        }

        launch {
            flow.collect {
                stateFlow.value = it
            }
        }
    }
}

fun stated() {
    val flow = flowOf(0, 1, 2, 3, 4, 5)
    runBlocking {
        val stateFlow = flow.stateIn(scope = this, started = SharingStarted.WhileSubscribed(5000), initialValue = 0)

        launch {
            stateFlow.collect { value -> println("A: $value") }
        }
        launch {
            stateFlow.collect { value -> println("B: $value") }
        }
    }
}
