package scopecontainedactivity

import Activity
import kotlinx.coroutines.*

class MainActivity : Activity() {
    private val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    fun onClick() {
        scope.launch() {
            // Do stuff to handle button clicks
        }
    }
}
