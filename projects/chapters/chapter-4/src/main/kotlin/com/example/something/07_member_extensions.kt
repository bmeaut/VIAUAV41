package com.example.something

class Queue(val length: Int) {
    fun String.process() {
        println(length) // String's length
        println(this.length) // String's length
        println(this@Queue.length) // Queue's length
    }
}


abstract class Validator {
    protected abstract fun String.isValid(): Boolean

    fun validate(str: String?): Boolean {
        return str != null && str.isValid()
    }
}

class EmailValidator : Validator() {
    override fun String.isValid(): Boolean {
        return this.contains('@')
    }
}
