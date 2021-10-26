fun main() {
    println("".isEmpty()) // true
    println("kitten".isEmpty()) // false
    println("kitten".indexOf('t')) // 2
    println("KitTeN".lowercase()) // "kitten"

    println("".isNotEmpty()) // false
    println("   \t   \n\t ".isBlank()) // true
    println("   word       ".isNotBlank()) // true
    println("kitten".lastIndexOf('t')) // 3
    println("kitten".replaceFirstChar { it.titlecase() }) // "Kitten"
    println("   kitten     ".trim()) // "kitten"
    println("kitten".reversed()) // "nettik"

    println(String(charArrayOf('a', 'b', 'c')))
    println(String(byteArrayOf(0x68, 0x65, 0x78)))

    val map = """
        |X X
        | X ${2 + readLine()!!.toInt()}
        |X X
    """.trimIndent()

    println("---")
    println(map)
    println("---")
}
