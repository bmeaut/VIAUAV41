package invariance

import contravariance.HandoffPoint
import covariance.PickupPoint
import variance.Car
import variance.Tesla

interface Garage<T : Car> : PickupPoint<T>, HandoffPoint<T>

fun emptyGarage(garage: Garage<out Car>) {
    while (true) {
        val car: Car = garage.take() ?: break
        println("Removed $car")
    }
}

fun parkTesla(garage: Garage<in Tesla>) {
    garage.park(Tesla())
}

fun useGarage(garage: Garage<*>) {
    // Use non-generic functions?
}

fun main() {
    val garage: Garage<Car> = object : Garage<Car> {
        override fun take(): Car? { TODO() }
        override fun park(car: Car) { TODO() }
    }
    val teslaGarage: Garage<Tesla> = object :Garage<Tesla> {
        override fun take(): Tesla? { TODO() }
        override fun park(car: Tesla) { TODO() }
    }

    emptyGarage(garage)
    emptyGarage(teslaGarage)

    parkTesla(garage)
    parkTesla(teslaGarage)

    useGarage(garage)
    useGarage(teslaGarage)
}
