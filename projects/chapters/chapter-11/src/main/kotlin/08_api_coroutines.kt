import javafx.scene.control.TableView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface CoroutineApi {
    suspend fun search(query: String): List<JobSummary>
    suspend fun getDetails(jobId: String): JobDetails
}

class CoroutineApiImpl : CoroutineApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override suspend fun search(query: String): List<JobSummary> {
        return withContext(Dispatchers.IO) {
            blockingApi.search(query)
        }
    }

    override suspend fun getDetails(jobId: String): JobDetails {
        return withContext(Dispatchers.IO) {
            blockingApi.getDetails(jobId)
        }
    }
}

fun getJobDetailsCoroutinesApi(query: String, tableView: TableView<JobDetails>) {
    val api: CoroutineApi = CoroutineApiImpl()

    GlobalScope.launch(Dispatchers.Main) {
        val jobSummaries = api.search(query)
        val details = jobSummaries.map { summary ->
            api.getDetails(summary.id)
        }
        tableView.setData(details)
    }
}
