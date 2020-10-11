package also

class Person(val name: String)

fun log(person: Person) {
    println("$person was created")
}

fun register(name: String): Person {
    return Person(name).also {
        log(it)
    }
}
