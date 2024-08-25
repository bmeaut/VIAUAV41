
fun main() {
    val mutable = mutableListOf(2, 3, 5, 7)
    mutable += 11
    println(mutable)

    var readOnly = listOf(2, 3, 5, 7)
    readOnly += 11
    println(readOnly)
}
