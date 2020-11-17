package foo

class Bar

inline fun <reified T> printType(t: T) {
    println("I think '$t' is ${T::class}")
}

inline fun <reified R> List<*>.filterType(): List<R> {
    val result = mutableListOf<R>()
    for (element in this) {
        if (element is R) {
            result.add(element)
        }
    }
    return result
}

fun main() {
    printType("hi")  // I think 'hi' is class kotlin.String
    printType(Bar()) // I think 'Bar' is class foo.Bar

    val list = listOf(1, 'o', 992.5233, 2, 25.21, "foo", 17)
    println(list.filterType<Double>()) // [992.5233, 25.21]
}
