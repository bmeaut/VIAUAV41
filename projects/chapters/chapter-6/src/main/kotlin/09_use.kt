import java.io.File
import java.io.PrintWriter

fun main() {

    PrintWriter(File("test.txt")).use { writer ->
        writer.println("Hello World")
    }

}
