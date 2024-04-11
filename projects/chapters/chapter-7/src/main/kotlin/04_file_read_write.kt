import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val file = File("build.gradle.kts")
    val lines = mutableListOf<String>()
    val reader = BufferedReader(FileReader(file))
    var line: String? = reader.readLine()
    while (line != null) {
        lines.add(line)
        line = reader.readLine()
    }
    reader.close()
}

fun `example of use`(file: File) {
    file.bufferedReader().use { reader ->
        val lines = mutableListOf<String>()
        var line: String? = reader.readLine()
        while (line != null) {
            lines.add(line)
            line = reader.readLine()
        }
        lines
    }
}

fun `example of forEachLine`(file: File) {
    val lines = mutableListOf<String>()
    file.forEachLine { lines.add(it) }
}

fun `example of readLines`(file: File) {
    val lines: List<String> = file.readLines()
}

fun `example of useLines`(file: File) {
    val avg: Double = file.useLines { lines: Sequence<String> ->
        lines.map { it.length }.average()
    }
}
