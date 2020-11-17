package variance

open class Car
class Tesla : Car()
class Fiat : Car()
class Audi : Car()

interface Garage<T : Car> {
    fun park(car: T)
    fun take(): T
}

fun testGarage(garage: Garage<Car>) {
    // Empty the garage
    while (true) {
        val car: Car = garage.take() ?: break
        println("Removed $car")
    }
    // Park a new car
    garage.park(Car())
}

class CarGarage: Garage<Car> {
    override fun park(car: Car) { TODO() }
    override fun take(): Car { TODO() }
}

class TeslaGarage: Garage<Tesla> {
    override fun park(car: Tesla) { TODO() }
    override fun take(): Tesla { TODO() }
}

fun main() {
    testGarage(CarGarage())
//    testGarage(teslaGarage)
}
