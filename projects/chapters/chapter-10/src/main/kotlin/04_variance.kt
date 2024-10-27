package variance

interface Snack
class Pretzel : Snack
class Donut : Snack

interface Box<T : Snack> {
    fun insert(snack: T)
    fun take(): T?
}

fun testBox(box: Box<Snack>) {
    // Empty the box
    while (true) {
        val snack: Snack = box.take() ?: break
        println("Removed $snack")
    }
    // Add a new snack
    box.insert(Donut())
}

class SnackBox : Box<Snack> {
    override fun insert(snack: Snack) { TODO() }
    override fun take(): Snack { TODO() }
}

class PretzelBox : Box<Pretzel> {
    override fun insert(snack: Pretzel) { TODO() }
    override fun take(): Pretzel { TODO() }
}

fun main() {
    testBox(SnackBox())
//    testBox(PretzelBox())
}
