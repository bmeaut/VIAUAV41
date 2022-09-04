import java.io.File
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PersistentString(key: String) : ReadWriteProperty<Any?, String> {
    private var cachedValue: String

    init {
        cachedValue = File("${key}.txt")
            .takeIf { it.exists() }
            ?.readText()
            ?: ""
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return cachedValue
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        cachedValue = value
        val file = File("${property.name}.txt")
        file.writeText(value)
    }
}

object PersistentStringFactory
    : PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, String>> {
    private val keys = mutableSetOf<String>()

    override operator fun provideDelegate(
        thisRef: Any?,
        property: KProperty<*>
    ): ReadWriteProperty<Any?, String> {
        require(property.name !in keys) { "No duplicates allowed in PersistentStrings" }
        keys += property.name
        return PersistentString(property.name)
    }
}

fun persistentString()
        : PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, String>> {
    return PersistentStringFactory
}

fun main() {
    var str by persistentString()
    println("str is: $str")
    str = "hello"
}
