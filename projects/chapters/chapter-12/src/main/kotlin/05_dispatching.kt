package dispatch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val flow = flow {
    for (i in 1..10) {
        delay(1000)
        emit(i)
    }
}

fun main() {
    runBlocking {
        launch(Dispatchers.Main) {
            flow
                .filter {
                    println("Filtering on ${Thread.currentThread()}")
                    it % 2 == 0
                }
                .flowOn(Dispatchers.Default)
                .map {
                    delay(200L)
                    println("Mapping on ${Thread.currentThread()}")
                    it * 10
                }
                .collect {
                    println(it)
                }
        }
    }
}
