package com.example.something.multiply

inline fun repeat(times: Int, actions: () -> Unit) {
    for (i in 0 until times) {
        actions()
    }
}

fun String.multiply(times: Int): String {
    var result = ""
    repeat(times) {
        result += this
    }
    return result
}
