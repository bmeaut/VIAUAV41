package extras

fun main() {
    val badSet = BadInstrumentedHashSet<Int>()
    badSet.addAll(listOf(1, 2, 3, 4, 5))
    println(badSet.addCount) // 10

    val goodSet = GoodInstrumentedSet<Int>(HashSet())
    goodSet.addAll(listOf(1, 2, 3, 4, 5))
    println(goodSet.addCount) // 5

    val set = InstrumentedSet<Int>()
    set.addAll(listOf(1, 2, 3, 4, 5))
    println(set.addCount) // 5
}


class InstrumentedSet<E>(
    private val set: MutableSet<E> = HashSet()
) : MutableSet<E> by set {
    var addCount = 0

    override fun add(element: E): Boolean {
        addCount++
        return set.add(element)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        addCount += elements.size
        return set.addAll(elements)
    }
}
