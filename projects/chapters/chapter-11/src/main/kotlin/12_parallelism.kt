package parallelism

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun getData(index: Int): Double {
    delay(1000L)
    return index.toDouble() * 2
}

fun main() {
    GlobalScope.launch {
        val result = getData(1) + getData(2)
        println("Result is $result")
    }




    Thread.sleep(2000L)
}
