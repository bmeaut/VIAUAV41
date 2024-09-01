//region Helpers
@file:Suppress("UNUSED_VARIABLE")

fun getGrade() = 3

fun calculateRating() = 8
//endregion

fun main() {

    //@formatter:off

    val grade: Int = getGrade()
    when (grade) {
        1 -> {
            println("Failed")
        }
        2 -> { println("Adequate") }
        3 -> { println("Average") }
        4 -> { println("Good") }
        5 -> { println("Excellent") }
        else -> {
            /* "This shouldn't happen" */
            throw RuntimeException("Invalid grade!")
        }
    }

    //@formatter:on

    when (val rating = calculateRating()) {
        0 -> {
            println("Terrible")
        }
        1, 2, 3 -> println("Bad")
        in 4..6 -> println("Average")
        in 7..<10 -> println("Good")
        10 -> println("Perfect")
    }


    val enabled = false
    when (enabled) {
        true -> println("Enabled!")
        false -> println("Disabled :(")
    }


    fun check1(x: Int, y: Int) = x % 2 == y
    fun check2(x: Int, y: Int) = x * y < 100

    when {
        x < 40 -> println("x is too small")
        y in 0..50 -> println("y is in invalid range")
        x % y == 0 -> println("y should divide x")
        !check1(x, y) || !check2(x, y) -> {
            println("x and y didn't pass advanced validation")
        }
    }

}
