import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun compute(question: String): Int {
    delay(236_682_000_000_000_000L)
    return 42
}

fun demoCoroutines() {
    GlobalScope.launch {
        println("Asking question")
        val result = compute("life, universe, and everything")
        println("Answer was: $result")
        delay(1000)
        println("Forty-two?!")
    }
}
