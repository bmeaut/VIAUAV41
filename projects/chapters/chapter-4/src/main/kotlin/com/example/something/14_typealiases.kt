package com.example.something

interface View {
    fun setOnClickListener(listener: () -> Unit)
}

typealias OnClickListener = () -> Unit

interface View2 {
    fun setOnClickListener(listener: OnClickListener)
}
