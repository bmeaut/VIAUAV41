@file:Suppress("UNUSED_VARIABLE")

fun main() {

    //region Helpers
    val age = 15
    val drinkingAge = 21
    val adultAge = 18
    val a = 2
    val b = 5
    //endregion

    if (age < drinkingAge) {
        println("We can't serve you")
    } else {
        println("Have a beer")
    }

    val discount = if (age < adultAge) {
        println("Calculating discount")
        val diff = adultAge - age
        100 - diff * 5
    } else {
        println("No discount available")
        0
    }

    val max = if (a > b) a else b

}
