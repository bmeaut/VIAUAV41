fun main() {
    val words = listOf("camel", "pizza", "mug", "box", "shirt")

    val wordSequence: Sequence<String> = words.asSequence()

    val result: Int = words.asSequence()
        .filter { it > "kite" }     // the ones after "kite"
        .map { it.length }          // the length of those
        .filter { it <= 4 }         // the ones with <= 4 length
        .first()
    println("Result: $result")

    generateSequence(1) { x -> x * 2 }
        .take(20)
        .forEach(::println)
}
