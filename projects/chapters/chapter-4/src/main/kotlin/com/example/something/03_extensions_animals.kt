package com.example.something

abstract class Animal {
    open fun identify() {
        println("This is an animal.")
    }
}

class Cat : Animal() {
    override fun identify() {
        println("This is a cat!")
    }
}

fun main() {
    val animal: Animal = Cat()
    animal.identify() // This is a cat!
}

//abstract class Animal
//
//fun Animal.identify() {
//    println("This is an animal.")
//}
//
//class Cat() : Animal()
//
//fun Cat.identify() {
//    println("This is a cat!")
//}
//
//fun main() {
//    val animal: Animal = Cat()
//    animal.identify() // This is an animal.
//}
