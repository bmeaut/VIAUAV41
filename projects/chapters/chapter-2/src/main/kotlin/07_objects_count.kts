class Document1 {
    object Counter {
        var count: Int = 0
    }

    val id = Counter.count++
}

repeat(5) {
    Document1()
}
println(Document1.Counter.count) // 5


class Document2 {
    companion object Counter {
        var count: Int = 0
    }

    val id = Counter.count++
}

repeat(5) {
    Document2()
}
println(Document2.count) // 5
