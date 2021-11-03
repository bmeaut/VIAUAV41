import kotlin.math.sqrt
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Lazy<T>(private val initializer: () -> T) : ReadOnlyProperty<Any?, T> {
    private var value: T? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = initializer()
        }
        return value!!
    }
}

fun <T> lazy(initializer: () -> T): ReadOnlyProperty<Any?, T> = Lazy(initializer)

val pi by lazy {
    println("Computing")
    sqrt(6 * (1..1_000_000_000).sumOf {
        1.toDouble() / it / it
    })
}

fun main() {
    println(pi)
    println(pi)
    println(pi)
}
