package upperbounds

fun <N : Number> printNumber(number: N): N {
    println(number.toDouble())
    return number
}

fun usePrintNumber() {
    val l: Long = printNumber(23L)
}

fun <T : Any> testEquality(t1: T, t2: T): Boolean {
    return t1.equals(t2)
}

interface Writer {
    fun write(str: String)
}

interface Reader {
    fun read(): String
}

fun <T> readWriteStuff(t: T) where T : Reader, T : Writer {
    val str = t.read()
    t.write(str)
}
