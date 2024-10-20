class Complex(val re: Double, val im: Double) {
    override fun toString() = "$re${if (im < 0) "" else "+"}${im}i"

    operator fun plus(other: Complex) =
        Complex(this.re + other.re, this.im + other.im)

    operator fun plus(other: Double) =
        Complex(this.re + other, this.im)
}

operator fun Complex.unaryMinus() = Complex(-re, -im)

operator fun Double.plus(other: Complex) = Complex(this + other.re, other.im)

fun main() {
    val a = Complex(4.0, 3.0)
    val b = Complex(8.0, 2.0)
    println(a + b) // 12.0+5.0i

    println(Complex(3.0, 9.0) + 20.0) // 23.0+9.0i

    val x = Complex(10.0, 20.0)
    println(-x) // -10.0-20.0i

    println(20.0 + Complex(3.0, 9.0)) // 23.0+9.0i
}
