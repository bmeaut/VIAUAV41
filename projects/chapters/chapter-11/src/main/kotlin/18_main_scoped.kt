package scopedactivity

import Activity
import kotlinx.coroutines.*

class MainActivity : Activity(), CoroutineScope {
    override val coroutineContext = Job() + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }

    fun onClick() {
        launch() {
            // Do stuff to handle button clicks
        }
    }
}
