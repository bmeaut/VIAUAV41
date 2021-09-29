package com.example.something

//region Helpers
abstract class Context

abstract class Activity : Context()

class Toast {
    companion object {
        const val LENGTH_SHORT = 1
        const val LENGTH_LONG = 2

        fun makeText(context: Context, text: String, duration: Int): Toast {
            TODO("return a Toast instance")
        }
    }

    fun show() {
        // shows a Toast somehow
    }
}
//endregion

class MainActivity : Activity() {

    fun onCreate() {
        Toast.makeText(this, "Network timed out", Toast.LENGTH_LONG).show()

        toast("Network timed out")
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
