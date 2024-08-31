import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun loadResult(): Double = coroutineScope {
    val data1 = async { getData(1) }
    val data2 = async { getData(2) }

    data1.await() + data2.await()
}

fun main() {
    GlobalScope.launch { // this: CoroutineScope
        val result = loadResult()
        println("Result is $result")
    }
}
