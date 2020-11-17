
interface Task<T> {
    fun execute(): T
}

fun main() {
    val stringResultTask = object: Task<String> {
        override fun execute(): String {
            return "This is a result string"
        }
    }
    val str = stringResultTask.execute()

    val noResultTask = object: Task<Unit> {
        override fun execute() {
            println("Do something!")
        }
    }
    noResultTask.execute()
}
