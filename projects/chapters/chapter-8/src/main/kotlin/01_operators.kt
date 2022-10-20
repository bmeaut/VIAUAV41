class MyInt(private val value: Int) : Comparable<MyInt> {
    fun toInt(): Int = value
    override fun toString() = value.toString()

    operator fun plus(other: MyInt): MyInt {
        return MyInt(this.value + other.value)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MyInt)
            return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value
    }

    override operator fun compareTo(other: MyInt): Int {
        return this.value.compareTo(other.value)
    }
}

operator fun MyInt.unaryMinus(): MyInt {
    return MyInt(-this.toInt())
}

fun main() {
    val a = MyInt(4)
    val b = MyInt(8)
    println(a + b) // 12

    val x = MyInt(4)
    println(-x) // -4

    println(MyInt(10) == MyInt(10)) // true
    println(MyInt(10) == MyInt(0)) // false
    println(MyInt(10) != MyInt(0)) // true

    println(MyInt(5) < MyInt(10)) // true
    println(MyInt(5) > MyInt(10)) // false
}
