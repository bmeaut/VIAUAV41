package covariance

import variance.Car
import variance.Tesla

interface PickupPoint<out T : Car> {
    fun take(): T?
}

fun testPickup(pickupPoint: PickupPoint<Car>) {
    // Take a car from the pickup point
    val car: Car = requireNotNull(pickupPoint.take())
    println("Driving a $car")
}

fun main() {
    val carPickup = object: PickupPoint<Car> {
        override fun take(): Car? { TODO() }
    }
    testPickup(carPickup)

    val teslaPickup = object: PickupPoint<Tesla> {
        override fun take(): Tesla? { TODO() }
    }
    testPickup(teslaPickup)
}
