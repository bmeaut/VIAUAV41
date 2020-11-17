import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val job = GlobalScope.launch {
        println("Job is running...")
        delay(500)
        println("Job is done!")
    }

    GlobalScope.launch {
        println("Second coroutine started, will wait for first")
        job.join()
        println("All done!")
    }

    Thread.sleep(1000)
}
