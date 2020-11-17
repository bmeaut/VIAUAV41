package wrappingcallbacks

import Task
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun getUsername(): Task<String> {
    return object : Task<String>() {
        init {
            thread {
                Thread.sleep(2000)
                this.listener?.onComplete("admin")
            }
        }
    }
}

fun bad() {
    getUsername().setListener(object : Task.OnCompleteListener<String?> {
        override fun onComplete(result: String?) {
            println("Success: $result")
        }

        override fun onError(exception: Throwable?) {
            println("Error: $exception")
        }
    })

    Thread.sleep(2000)
}

suspend fun <T> Task<T>.await(): T = suspendCoroutine { cont ->
    this.setListener(object : Task.OnCompleteListener<T> {
        override fun onComplete(result: T) {
            cont.resume(result)
        }

        override fun onError(exception: Throwable) {
            cont.resumeWithException(exception)
        }
    })
}

suspend fun main() {
    val name = getUsername().await()
    println(name)
}
