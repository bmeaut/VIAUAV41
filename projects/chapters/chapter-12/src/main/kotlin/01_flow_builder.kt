package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun first() {
    runBlocking {
        val flow = flow {
            emit("A")
            emit("B")
            emit("C")
        }

        launch {
            println("Starting collector")
            flow.collect {
                println(it)
            }
            println("Done collecting!")
        }
    }
}

fun second() {
    runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(1000)
                emit(i)
            }
        }

        launch {
            flow.collect {
                println(it)
            }
        }
    }
}

fun getFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        emit(i)
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

        "hello".repeat(5)
    }
}
