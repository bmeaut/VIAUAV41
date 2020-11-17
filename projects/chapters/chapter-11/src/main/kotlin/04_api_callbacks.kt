import javafx.application.Platform
import javafx.scene.control.TableView

interface CallbackApi {
    fun search(query: String, callback: (List<JobSummary>) -> Unit)
    fun getDetails(jobId: String, callback: (JobDetails) -> Unit)
}

class CallbackApiImpl : CallbackApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String, callback: (List<JobSummary>) -> Unit) {
        Thread {
            val result = blockingApi.search(query)
            Platform.runLater {
                callback(result)
            }
        }.start()
    }

    override fun getDetails(jobId: String, callback: (JobDetails) -> Unit) {
        Thread {
            val result = blockingApi.getDetails(jobId)
            Platform.runLater {
                callback(result)
            }
        }.start()
    }
}

fun getJobDetailsWithCallbacks(query: String, tableView: TableView<JobDetails>) {
    val callbackApi: CallbackApi = CallbackApiImpl()

    val results = mutableListOf<JobDetails>()
    callbackApi.search(query) { jobSummaries ->
        for (job in jobSummaries) {
            callbackApi.getDetails(job.id) { jobDetails ->
                results.add(jobDetails)

                if (results.size == jobSummaries.size) {
                    tableView.setData(results)
                }
            }
        }
    }
}
