import kotlinx.coroutines.*

class Screen {
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun performWork() {
        scope.launch {
            // Run async work
        }
    }

    fun onDestroy() {
        scope.cancel()
    }
}
