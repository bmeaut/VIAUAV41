import javafx.scene.control.TableView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface CoroutineApi {
    suspend fun search(query: String): List<ShowSummary>
    suspend fun getDetails(id: Int): ShowDetails
}

class CoroutineApiImpl : CoroutineApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override suspend fun search(query: String): List<ShowSummary> {
        return withContext(Dispatchers.IO) {
            blockingApi.search(query)
        }
    }

    override suspend fun getDetails(id: Int): ShowDetails {
        return withContext(Dispatchers.IO) {
            blockingApi.getDetails(id)
        }
    }
}

fun getShowDetailsCoroutinesApi(query: String, tableView: TableView<ShowDetails>) {
    val api: CoroutineApi = CoroutineApiImpl()

    GlobalScope.launch(Dispatchers.Main) {
        val showSummaries = api.search(query)
        val details = showSummaries.map { summary ->
            api.getDetails(summary.id)
        }
        tableView.setData(details)
    }
}
