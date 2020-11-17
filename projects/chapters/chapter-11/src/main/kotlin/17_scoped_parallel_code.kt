import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch { // this: CoroutineScope
        val data1 = async { getData(1) }
        val data2 = async { getData(2) }

        val result = data1.await() + data2.await()
        println("Result is $result")
    }



    Thread.sleep(1000L)
}
