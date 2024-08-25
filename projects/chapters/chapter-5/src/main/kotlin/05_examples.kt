fun `example of forEach`() {
    val numbers = listOf(1, 2, 3, 4, 5)
    numbers.forEach {
        println(it)
    }
}

fun `example of map`() {
    class Person(val name: String)

    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val doubled = numbers.map { it * 2 } // 2, 4, 6, 8, 10, 12
    val doubles = numbers.map { it.toDouble() } // 1.0, 2.0, ...

    val people: List<Person> = listOf(
        Person("Katie"), Person("Vicki"), Person("Annette")
    )
    val names: List<String> = people.map { it.name }
}

fun `example of flatMap`() {
    val words = listOf("hello", "there")

    val result = words.flatMap { word ->
        word.toList() // returns a List<Char>
    }

    println(result) // [h, e, l, l, o, t, h, e, r, e]
}

fun `old example of flatMap`() {
    val ints = listOf(1, 2, 3)

    val result = ints.flatMap {
        val list = mutableListOf<Int>()
        for (i in 0 until it) {
            list.add(it)
        }
        list
    }

    println(result) // [1, 2, 2, 3, 3, 3]
}

fun `example of any and all`() {
    val numbers = listOf(7, 2, 8, 3, 7, 1)
    val allOdd = numbers.all { it % 2 == 1 } // false, fails at 2

    val names = listOf("Jane", "Kyra", "Leah")
    val anyK = names.any { it.startsWith("K") } // true, succeeds at Kyra
}

fun `combined example`() {
    val words = listOf("camel", "pizza", "mug", "box", "shirt")

    val result = words
        .filter { it > "kite" }     // the ones after "kite"
        .map { it.length }          // the length of those
        .filter { it <= 4 }         // the ones with <= 4 length
        .first()                    // the first one of these
}
