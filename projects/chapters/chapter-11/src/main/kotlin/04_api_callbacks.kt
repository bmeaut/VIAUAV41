import javafx.application.Platform
import javafx.scene.control.TableView

interface CallbackApi {
    fun search(query: String, callback: (List<ShowSummary>) -> Unit)
    fun getDetails(id: Int, callback: (ShowDetails) -> Unit)
}

class CallbackApiImpl : CallbackApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String, callback: (List<ShowSummary>) -> Unit) {
        Thread {
            val result = blockingApi.search(query)
            Platform.runLater {
                callback(result)
            }
        }.start()
    }

    override fun getDetails(id: Int, callback: (ShowDetails) -> Unit) {
        Thread {
            val result = blockingApi.getDetails(id)
            Platform.runLater {
                callback(result)
            }
        }.start()
    }
}

fun getShowDetailsWithCallbacks(query: String, tableView: TableView<ShowDetails>) {
    val callbackApi: CallbackApi = CallbackApiImpl()

    val results = mutableListOf<ShowDetails>()
    callbackApi.search(query) { showSummaries ->
        for (show in showSummaries) {
            callbackApi.getDetails(show.id) { showDetails ->
                results.add(showDetails)

                if (results.size == showSummaries.size) {
                    tableView.setData(results)
                }
            }
        }
    }
}
