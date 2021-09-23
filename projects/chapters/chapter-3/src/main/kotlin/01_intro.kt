//public static boolean isEmpty(String str) {
//    return str.length() == 0;
//}

class Person(val name: String, val age: Int)

// val person: Person = null

fun main() {
    var person: Person? = null
    person = Person("Ann", 37)
    person = null
}

fun printName(person: Person?) {
    //    println(person.name)

    if (person != null) {
        println(person.name)
    }
}

abstract class Animal
class Dog(val name: String) : Animal()

fun dogCheck(animal: Animal) {
    if (animal is Dog) {
        println("${animal.name} is a good boy")
    }
}

