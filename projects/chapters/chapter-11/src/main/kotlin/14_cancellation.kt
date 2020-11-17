@file:Suppress("DuplicatedCode")

import kotlinx.coroutines.*

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


fun saveToServer(entity: String): Unit = TODO()
fun saveToDisk(entity: String): Unit = TODO()

suspend fun processEntities(entities: List<String>) = withContext(Dispatchers.IO) {
    entities.forEach { entity ->
        saveToServer(entity)
        saveToDisk(entity)
    }
}
