import java.math.BigInteger
import kotlin.random.Random

suspend fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, java.util.Random())
