fun filterShortWords(words: List<String>): List<String> {
    val result: MutableList<String> = mutableListOf()
    for (word in words) {
        if (word.length < 5) {
            result.add(word)
        }
    }
    return result
}

fun filterOddNumbers(numbers: List<Int>): List<Int> {
    val result: MutableList<Int> = mutableListOf()
    for (number in numbers) {
        if (number % 2 == 1) {
            result.add(number)
        }
    }
    return result
}

inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (element in this) {
        if (predicate(element)) {
            result.add(element)
        }
    }
    return result
}

fun main() {
    val words = arrayOf("Nitwit", "Blubber", "Oddment", "Tweak")

    words.filter({ word: String -> word.length < 5 })
    words.filter({ word -> word.length < 5 })
    words.filter({ it.length < 5 })
    words.filter { it.length < 5 }

    val numbers = listOf(6, 28, 496, 8128)

    numbers.filter { it % 2 == 1 }
}
