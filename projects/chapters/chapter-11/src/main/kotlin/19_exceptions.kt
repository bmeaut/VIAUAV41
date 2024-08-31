import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.Random

suspend fun failedValueFetch(): Int = withContext(Dispatchers.Default) {
    if (Random.nextBoolean()) {
        throw RuntimeException("Oops!")
    }
    return@withContext 13
}

suspend fun tryToFetchValue() {
    try {
        println(failedValueFetch())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
    GlobalScope.launch {
        try {
            failedValueFetch()
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            // Handle other exceptions
        }
    }
}
