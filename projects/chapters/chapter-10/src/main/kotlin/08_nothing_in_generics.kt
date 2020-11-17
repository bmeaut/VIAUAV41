package nothinggenerics

sealed class Result<out T : Any>
class Success<T : Any>(val result: T) : Result<T>()
class Error(val throwable: Throwable) : Result<Nothing>()

fun getData(): Result<String> {
    return Error(RuntimeException("oh bother"))
}

open class Person
class Employee : Person()

fun getPerson() : Result<Person> {
    return Success<Employee>(Employee())
}
