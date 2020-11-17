import javafx.scene.control.TableView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun getJobDetailsCoroutines(query: String, tableView: TableView<JobDetails>) {
    val api: BlockingApi = BlockingApiImpl()

    GlobalScope.launch {
        val jobSummaries = api.search(query)
        val details = jobSummaries.map {  summary ->
            api.getDetails(summary.id)
        }
        tableView.setData(details)
    }
}
