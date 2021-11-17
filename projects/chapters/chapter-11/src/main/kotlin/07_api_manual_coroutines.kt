import javafx.scene.control.TableView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun getShowDetailsCoroutines(query: String, tableView: TableView<ShowDetails>) {
    val api: BlockingApi = BlockingApiImpl()

    GlobalScope.launch {
        val showSummaries = api.search(query)
        val details = showSummaries.map { summary ->
            api.getDetails(summary.id)
        }
        tableView.setData(details)
    }
}
