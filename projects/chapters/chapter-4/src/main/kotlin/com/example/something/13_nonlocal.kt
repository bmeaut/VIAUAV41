package com.example.something.nonlocal

import kotlin.random.Random

inline fun repeat(times: Int, actions: () -> Unit) {
    for (i in 0 until times) {
        actions()
    }
}

inline fun funky(crossinline body: () -> Unit) {
    val runnable = object : Runnable {
        override fun run() {
            body()
        }
    }
    runnable.run()
}

fun main() {
    repeat(10) {
        if (Random.nextDouble() > 0.6) {
            println("Failure")
            return@repeat
        }
        println("Success")
    }

    funky {
        println("This is really quite complicated!")
    }
}

