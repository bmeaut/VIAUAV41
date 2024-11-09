package concurr

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun getFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        coroutineScope {
            // This will throw an exception
            launch { emit(i) }
            launch { emit(i) }
        }
    }
}

fun main() {
    runBlocking {
        launch {
            getFlow().collect { println("A: $it") }
        }
        launch {
            getFlow().collect { println("B: $it") }
        }
    }
}
