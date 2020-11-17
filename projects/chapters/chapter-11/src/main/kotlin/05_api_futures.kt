import javafx.application.Platform
import javafx.scene.control.TableView
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.stream.Stream

interface FutureApi {
    fun search(query: String): CompletableFuture<List<JobSummary>>
    fun getDetails(jobId: String): CompletableFuture<JobDetails>
}

class FutureApiImpl : FutureApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String): CompletableFuture<List<JobSummary>> {
        return CompletableFuture.supplyAsync {
            blockingApi.search(query)
        }
    }

    override fun getDetails(jobId: String): CompletableFuture<JobDetails> {
        return CompletableFuture.supplyAsync {
            blockingApi.getDetails(jobId)
        }
    }
}

fun getJobDetailsWithFutures(query: String, tableView: TableView<JobDetails>) {
    val futureApi: FutureApi = FutureApiImpl()

    futureApi.search(query)
        .thenApply { summaries ->
            val futures = summaries.map { futureApi.getDetails(it.id) }
            Stream.of(*futures.toTypedArray())
                .map(CompletableFuture<JobDetails>::join)
                .collect(Collectors.toList())
        }
        .thenAccept { results: List<JobDetails> ->
            Platform.runLater {
                tableView.setData(results)
            }
        }
}
