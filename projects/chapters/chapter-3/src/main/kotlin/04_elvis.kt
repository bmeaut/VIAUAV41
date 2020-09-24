fun getMessage(): String? = null

fun messageLength() {
    val message: String? = getMessage()
    val length: Int = message?.length ?: 0
}

fun processInput() {
    while (true) {
        val input: String = readLine() ?: return
        println("Input was: $input")
    }
}

fun main() {
    processInput()
}
