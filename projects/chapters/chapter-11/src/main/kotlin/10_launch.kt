import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    val job = GlobalScope.launch {
        println("Job is running...")
        delay(500)
        println("Job is done!")
    }

    Thread.sleep(200L)
    if (job.isActive) {
        job.cancel()
    }


    Thread.sleep(2000L)
}
