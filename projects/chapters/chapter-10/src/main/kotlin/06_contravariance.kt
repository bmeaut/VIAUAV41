package contravariance

import variance.Car
import variance.Tesla

interface HandoffPoint<in T : Car> {
    fun park(car: T)
}

fun testHandoff(handoff: HandoffPoint<Tesla>) {
    handoff.park(Tesla())
}

fun main() {
    val teslaHandoff = object : HandoffPoint<Tesla> {
        override fun park(car: Tesla) { TODO() }
    }
    testHandoff(teslaHandoff)

        val carHandoff = object : HandoffPoint<Car> {
        override fun park(car: Car) { TODO() }
    }
    testHandoff(carHandoff)
}
