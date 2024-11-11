import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val stateFlow = MutableStateFlow<Int>(0)

    launch {
        stateFlow.collect { println(it) }
    }
    launch {
        stateFlow.value = 1
        delay(500)
        stateFlow.value = 1
        delay(500)
        stateFlow.value = 2
        stateFlow.value = 3
    }
}
