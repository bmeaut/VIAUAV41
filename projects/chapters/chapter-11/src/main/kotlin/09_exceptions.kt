import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
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

suspend fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, java.util.Random())
