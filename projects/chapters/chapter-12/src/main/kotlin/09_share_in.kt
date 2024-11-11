package sharein

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun manual() {
    val flow = flowOf(0, 1, 2, 3, 4, 5)

    runBlocking {
        val sharedFlow = MutableSharedFlow<Int>()

        launch {
            sharedFlow.collect { value -> println("A: $value") }
        }
        launch {
            sharedFlow.collect { value -> println("B: $value") }
        }

        launch {
            flow.collect { value ->
                sharedFlow.emit(value)
            }
        }
    }
}

fun shared() {
    val flow = flowOf(0, 1, 2, 3, 4, 5)

    runBlocking {
        val sharedFlow: SharedFlow<Int> = flow.shareIn(scope = this, SharingStarted.Lazily)

        launch {
            sharedFlow.collect { value -> println("A: $value") }
        }
        launch {
            sharedFlow.collect { value -> println("B: $value") }
        }
    }
}
