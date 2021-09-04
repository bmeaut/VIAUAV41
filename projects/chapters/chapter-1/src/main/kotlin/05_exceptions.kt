//region Helpers
@file:Suppress("unused", "UNUSED_VARIABLE")

class Customer(val name: String, val balance: Int)

interface Database {
    fun open()
    fun beginTransaction()
    fun insert(customer: Customer)
    fun commitTransaction()
    fun rollbackTransaction()
    fun close()
}

fun readUserInput() = readLine()!!
//endregion

fun useDatabase(db: Database) {

    db.open()
    db.beginTransaction()
    try {
        db.insert(Customer(name = "Ann", balance = 1_000_000))
        db.commitTransaction()
    } catch (e: IllegalStateException) {
        db.rollbackTransaction()
    } finally {
        db.close()
    }

}


fun main() {
    val input: String = readUserInput()
    val value: Double = try {
        input.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}
