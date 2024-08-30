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

fun `example of partition`() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val (odds, evens) = numbers.partition { it % 2 == 1 }

    println(odds) // [1, 3, 5, 7, 9]
    println(evens) // [2, 4, 6, 8, 10]
}

fun `example of groupBy`() {
    val names = listOf("Alex", "Sam", "Teo", "James", "Grey")
    val groups: Map<Int, List<String>> = names.groupBy { it.length }

    println(groups) // {4=[Alex, Grey], 3=[Sam, Teo], 5=[James]}
}

fun `example of chunked and windowed`() {
    val dailyTemperatures = listOf(21, 30, 26, 29, 29, 26, 30, 23, 27, 24, 23, 25, 30, 28)

    val sevenDayValues: List<List<Int>> = dailyTemperatures.windowed(7)
    println(sevenDayValues)

    val weeklyValues: List<List<Int>> = dailyTemperatures.chunked(7)
    println(weeklyValues)
}

fun `combined example`() {
    val words = listOf("camel", "pizza", "mug", "box", "shirt")

    val result = words
        .filter { it > "kite" }     // the ones after "kite"
        .map { it.length }          // the length of those
        .filter { it <= 4 }         // the ones with <= 4 length
        .first()                    // the first one of these
}
