import java.io.File
import java.io.FileInputStream

interface Reader {
    /**
     * Opens the reader. Every call to this method
     * must be followed by a call to [close] to
     * free up underlying resources, when the
     * reader is no longer being used.
     */
    fun open()

    /**
     * Closes the reader, freeing up any resources
     * associated with it.
     */
    fun close()

    /**
     * Reads bytes from the reader. The reader must
     * be open.
     *
     * @param byteCount The number of bytes to read.
     *                  Must be a positive number.
     */
    fun readBytes(byteCount: Int): ByteArray
}

class FileReader(private val fileName: String) : Reader {

    private var inputStream: FileInputStream? = null

    override fun open() {
        inputStream = FileInputStream(File(fileName))
    }

    override fun close() {
        inputStream?.close()
        inputStream = null
    }

    override fun readBytes(byteCount: Int): ByteArray {
        val input = checkNotNull(inputStream) { "Reader must be opened first" }
        require(byteCount > 0) { "Byte count has to be a positive number (was $byteCount)" }

        val arr = ByteArray(byteCount)
        input.read(arr)
        return arr
    }

}

fun main() {
    with(FileReader("build.gradle")) {
        open()
        val x = String(readBytes(30))
        println(x)
        val z = String(readBytes(20))
        println(z)
        close()
    }
}
