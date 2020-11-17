import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        delay(1000)
        println("Hello world!")
    }
}

fun main2() {
    runBlocking {
        launch {
            delay(1000)
            println("Hello world!")
        }
    }
}

suspend fun main3() {
    delay(1000)
    println("Hello world!")
}
