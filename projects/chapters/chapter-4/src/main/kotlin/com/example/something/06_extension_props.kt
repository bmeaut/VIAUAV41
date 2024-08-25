package com.example.something.props

class Person(var firstName: String, var lastName: String)

var Person.fullName: String
    get() = "$firstName $lastName"
    set(value) {
        val (first, last) = value.split(" ")
        this.firstName = first
        this.lastName = last
    }

fun main() {
    val person = Person("Buttermilk", "Cabbagepatch")
    println(person.fullName)
}
