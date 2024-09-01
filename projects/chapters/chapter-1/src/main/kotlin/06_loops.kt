//region Helpers
fun getEntries(): Iterator<Int> {
    return listOf(1, 2, 3, 4, 5).iterator()
}
//endregion

fun main() {

    val entries = getEntries()
    while (entries.hasNext()) {
        println("Entry: ${entries.next()}")
    }


    val myNumbers = listOf(1, 2, 5, 14, 42, 132, 429)
    for (number in myNumbers) {
        println(number)
    }


    for (i in 0..10) {
        print("$i ")
    } // 0 1 2 3 4 5 6 7 8 9 10

    println()

    for (i in 0..<10) {
        print("$i ")
    } // 0 1 2 3 4 5 6 7 8 9

    println()

    for (i in 10 downTo 1 step 2) {
        print("$i ")
    } // 10 8 6 4 2
}
