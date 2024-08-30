package com.example.something

fun getOperation(char: Char): (Int, Int) -> Int {
    return when (char) {
        '+' -> { a, b -> a + b }
        '-' -> { a, b -> a - b }
        '*' -> { a, b -> a * b }
        '/' -> { a, b -> a / b }
        else -> throw IllegalArgumentException("Unknown operator: $char")
    }
}

fun main() {
    val x = readln().toInt()
    val op = readln().first()
    val y = readln().toInt()

    val operation = getOperation(op)
    println(operation(x, y,))
}
