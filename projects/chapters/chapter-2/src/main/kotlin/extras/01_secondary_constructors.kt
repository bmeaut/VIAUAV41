package extras

import getCurrentYear

class Car {
    val model: String
    val year: Int
    var miles: Double = 0.0
    val age: Int

    constructor(
        model: String,
        year: Int,
    ) {
        this.model = model
        this.year = year
        age = getCurrentYear() - year
    }

    constructor(
        model: String,
        year: String,
        mileage: String,
    ) : this(model, year.toInt()) {
        miles = mileage.toDouble()
    }

}
