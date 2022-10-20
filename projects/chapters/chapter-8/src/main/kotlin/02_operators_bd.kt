import java.math.BigDecimal
import java.math.MathContext

fun solve1(
    a: BigDecimal, b: BigDecimal, c: BigDecimal
): Pair<BigDecimal, BigDecimal> {
    val sqrtD = b.multiply(b).subtract(BigDecimal(4).multiply(a).multiply(c)).sqrt(MathContext.UNLIMITED)
    val x1 = b.negate().add(sqrtD).divide(BigDecimal(2).multiply(a))
    val x2 = b.negate().subtract(sqrtD).divide(BigDecimal(2).multiply(a))
    return Pair(x1, x2)
}

//region Helpers
fun sqrt(bd: BigDecimal) = bd.sqrt(MathContext.UNLIMITED)
//endregion

fun solve2(
    a: BigDecimal, b: BigDecimal, c: BigDecimal
): Pair<BigDecimal, BigDecimal> {
    val sqrtD = sqrt(b * b - 4.toBigDecimal() * a * c)
    val x1 = (-b + sqrtD) / 2.toBigDecimal() * a
    val x2 = (-b - sqrtD) / 2.toBigDecimal() * a
    return Pair(x1, x2)
}
