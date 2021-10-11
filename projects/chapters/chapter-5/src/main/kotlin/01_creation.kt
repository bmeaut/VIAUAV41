fun main() {

    val list1: List<Int> = listOf(1, 2, 3)
    val list2: MutableList<Int> = mutableListOf(1, 2, 3)
    val list3: List<Any> = listOf(1, "two", 3.0)

    val scores = mutableMapOf(
        "Jim" to 2450,
        "Claire" to 1050,
        "Amanda" to 3700
    )

    val colours: Array<String> = arrayOf("green", "yellow", "purple")
    val primes: Array<Int> = arrayOf(2, 3, 5, 7, 11, 13, 17, 19)
    val primes2: IntArray = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)

    val numbers: Array<String> = Array(100) { i -> i.toString() }
    val strings: Array<String?> = arrayOfNulls(200)

    val x = IntArray(20)

}
