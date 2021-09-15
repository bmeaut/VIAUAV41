package delegatedprops

import kotlin.math.sqrt
import kotlin.properties.Delegates

class Constants {

    private var _pi: Double? = null
    val pi: Double
        get() {
            if (_pi == null) {
                // Some expensive computation
                val sum = (1..50_000).sumOf { 1.0 / it / it }
                _pi = sqrt(sum * 6.0)
            }
            return _pi!!
        }

    private var _e: Double? = null
    val e: Double
        get() {
            if (_e == null) {
                // Again, complex, expensive computation
                val sum = (0..20).sumOf { 1.0 / (1..it).fold(1, { a, x -> a * x }) }
                _e = sum
            }
            return _e!!
        }

}

class DelegatedConstants {

    val pi: Double by lazy {
        val sum = (1..50_000).sumOf { 1.0 / it / it }
        sqrt(sum * 6.0)
    }

}

class Person {
    var name: String by Delegates.observable("Megan") { property, oldValue, newValue ->
        println("Name changed from $oldValue to $newValue")
    }

    var age: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
        newValue > oldValue
    }
}
