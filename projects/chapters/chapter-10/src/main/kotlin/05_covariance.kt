package covariance

import variance.Snack
import variance.Pretzel

interface Dispenser<out T : Snack> {
    fun take(): T?
}

fun testDispenser(dispenser: Dispenser<Snack>) {
    // Take a snack from the dispenser
    val snack: Snack = requireNotNull(dispenser.take())
    println("Grabbing a $snack")
}

fun main() {
    val snackDispenser = object: Dispenser<Snack> {
        override fun take(): Snack? { TODO() }
    }
    testDispenser(snackDispenser)

    val pretzelDispenser = object: Dispenser<Pretzel> {
        override fun take(): Pretzel? { TODO() }
    }
    testDispenser(pretzelDispenser)
}
