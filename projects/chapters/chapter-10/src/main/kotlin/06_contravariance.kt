package contravariance

import variance.Snack
import variance.Pretzel

interface Collector<in T : Snack> {
    fun insert(snack: T)
}

fun testCollector(collector: Collector<Pretzel>) {
    collector.insert(Pretzel())
}

fun main() {
    val pretzelCollector = object : Collector<Pretzel> {
        override fun insert(snack: Pretzel) { TODO() }
    }
    testCollector(pretzelCollector)

    val snackCollector = object : Collector<Snack> {
        override fun insert(snack: Snack) { TODO() }
    }
    testCollector(snackCollector)
}
