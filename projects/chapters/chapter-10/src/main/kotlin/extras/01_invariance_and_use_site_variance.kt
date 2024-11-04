package extras

import invariance.Box
import variance.Snack
import variance.Pretzel


fun emptyBox(box: Box<out Snack>) {
    while (true) {
        val snack: Snack = box.take() ?: break
        println("Removed $snack")
    }
}

fun insertPretzel(box: Box<in Pretzel>) {
    box.insert(Pretzel())
}

fun useBox(box: Box<*>) {
    // Use non-generic functions?
}







fun main() {
    val snackBox: Box<Snack> = object : Box<Snack> {
        override fun take(): Snack? { TODO() }
        override fun insert(snack: Snack) { TODO() }
    }
    val pretzelBox: Box<Pretzel> = object :Box<Pretzel> {
        override fun take(): Pretzel? { TODO() }
        override fun insert(snack: Pretzel) { TODO() }
    }

    emptyBox(snackBox)
    emptyBox(pretzelBox)

    insertPretzel(snackBox)
    insertPretzel(pretzelBox)

    useBox(snackBox)
    useBox(pretzelBox)
}
