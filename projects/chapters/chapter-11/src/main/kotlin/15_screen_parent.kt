package parentscren

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Screen {
    private val parent = Job()

    fun performWork() {
        // Don't forget to include the parent!
        GlobalScope.launch(parent) {
            // Run async work
        }
    }

    fun onDestroy() {
        parent.cancel()
    }
}
