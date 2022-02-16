//region Helpers
fun getCurrentYear() = 2022 // TODO update this implementation next year
//endregion

class Car(val model: String, val year: Int) {
    var miles: Double = 0.0

    val age: Int

    init {
        age = getCurrentYear() - year
    }

    val description: String = "$model ($age years, $miles miles)"

    constructor(
        model: String,
        year: String,
        mileage: String,
    ): this(model, year.toInt()) {
        miles = mileage.toDouble()
    }

    constructor(data: Array<String>) : this(
        model = data[1],
        year = data[3],
        mileage = data[7],
    )
}

class Car2 {
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
