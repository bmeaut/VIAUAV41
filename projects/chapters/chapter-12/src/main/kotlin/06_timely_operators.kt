package timeops

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun bufferExample() {
    println("Buffer example:")

    runBlocking {
        val flow = flow {
            for (i in 1..10) {
                delay(600)
                emit(i)
            }
        }

        val initial = System.currentTimeMillis()
        flow
            .buffer()
            .collect {
                delay(400)
                println("${System.currentTimeMillis() - initial}ms: $it")
            }
    }
}

fun conflateExample() {
    println("Conflate example:")

    runBlocking {
        val flow = flow {
            for (i in 1..50) {
                delay(100)
                emit(i)
            }
        }

        val initial = System.currentTimeMillis()
        flow
            .conflate()
            .collect {
                delay(400)
                println("${System.currentTimeMillis() - initial}ms: $it")
            }
    }
}

fun debounceExample() {
    println("Debounce example:")

    runBlocking {
        val flow = flow {
            emit("k")
            delay(60)
            emit("ko")
            delay(80)
            emit("kot")
            delay(70)
            emit("kotl")
            delay(290)
            emit("kotli")
            delay(180)
            emit("kotlin")
        }

        flow
            .debounce(200)
            .collect { query ->
                println("Searching for $query...")
            }
    }
}


fun main() {
    bufferExample()
    conflateExample()
    debounceExample()
}
