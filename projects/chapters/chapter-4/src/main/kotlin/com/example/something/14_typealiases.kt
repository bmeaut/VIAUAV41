package com.example.something

interface View {
    fun setOnClickListener(listener: (Int, Int) -> Unit)
}

typealias OnClickListener = (Int, Int) -> Unit

interface View2 {
    fun setOnClickListener(listener: OnClickListener)
}
