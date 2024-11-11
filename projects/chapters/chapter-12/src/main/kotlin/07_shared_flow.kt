import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>()

    launch {
        sharedFlow.collect { println("A: $it") }
    }
    launch {
        delay(1000)
        sharedFlow.collect { println("B: $it") }
    }

    launch {
        sharedFlow.emit(1)
        delay(700)
        sharedFlow.emit(2)
        delay(1000)
        sharedFlow.emit(3)
    }
}
