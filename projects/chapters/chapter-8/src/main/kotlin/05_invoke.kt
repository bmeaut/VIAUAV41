import java.io.File

interface Command {
    operator fun invoke()
}

class CopyCommand(
    private val source: File,
    private val target: File
) : Command {
    override fun invoke() {
        source.copyTo(target)
    }
}

fun main() {
    val command: Command = CopyCommand(File("build.gradle"), File("build.gradle.old"))
    command.invoke()
    command()
}
