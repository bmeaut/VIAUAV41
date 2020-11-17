import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun main() {

    val singleThreadExecutor: Executor = Executors.newSingleThreadExecutor()
    val threadpoolExecutor: Executor = Executors.newFixedThreadPool(8)

    singleThreadExecutor.execute {
        println("I'm in a Runnable!")
    }

}
