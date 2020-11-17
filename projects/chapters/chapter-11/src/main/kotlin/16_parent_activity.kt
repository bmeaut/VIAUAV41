package parentactivity

import Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    private val parent = Job()

    override fun onDestroy() {
        super.onDestroy()
        parent.cancel()
    }

    fun onClick() {
        GlobalScope.launch(parent + Dispatchers.Main) {
            // Do stuff to handle button clicks
        }
    }
}
