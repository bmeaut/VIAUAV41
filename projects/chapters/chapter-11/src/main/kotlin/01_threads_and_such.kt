fun main() {
    val thread = Thread {
        println("Printing from this thread!")
    }
    thread.name = "my-background-thread"
    thread.start()
    thread.join()
}
