@file:Suppress("DuplicatedCode")

import kotlinx.coroutines.*


suspend fun getData(index: Int): Double {
    delay(1000L)
    return index.toDouble() * 2
}

fun fetch() {
    val data1 = GlobalScope.async { getData(1) }
    val data2 = GlobalScope.async { getData(2) }

    GlobalScope.launch {
        val result = data1.await() + data2.await()
        println("Result is $result")
    }
}

fun main() {
    val job = GlobalScope.launch {
        println("This is job 1")
        delay(500)
        println("This was job 1")
    }

    GlobalScope.launch(context = job) {
        println("This is job 2")
        delay(500)
        println("This was job 2")
    }

    Thread.sleep(200L)
    job.cancel()


    Thread.sleep(2000)
}
