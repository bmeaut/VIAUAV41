fun main() {
    val primes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23)

    if (4 in primes) {
        println("4 is a prime!")
    }
    if (4 !in primes) {
        println("4 is not a prime!")
    }

    val names = mutableListOf("Alex", "Bola", "Charlie")
    println(names[0])
    names[0] = "Amal"
}
