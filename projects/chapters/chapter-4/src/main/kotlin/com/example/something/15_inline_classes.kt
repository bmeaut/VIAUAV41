package com.example.something

@JvmInline
value class Color(private val value: Int) {
    val red: Int
        get() = (value shr 16) and 0xFF
    val green: Int
        get() = (value shr 8) and 0xFF
    val blue: Int
        get() = (value shr 0) and 0xFF
}

fun main() {
    val myColor = Color(0x005FFF)
    println(myColor.red)    // 0   (00)
    println(myColor.green)  // 95  (05)
    println(myColor.blue)   // 255 (FF)

    // val color: Color = 0
}
