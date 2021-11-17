import javafx.application.Platform
import javafx.scene.control.TableView
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.stream.Stream

interface FutureApi {
    fun search(query: String): CompletableFuture<List<ShowSummary>>
    fun getDetails(id: Int): CompletableFuture<ShowDetails>
}

class FutureApiImpl : FutureApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String): CompletableFuture<List<ShowSummary>> {
        return CompletableFuture.supplyAsync {
            blockingApi.search(query)
        }
    }

    override fun getDetails(id: Int): CompletableFuture<ShowDetails> {
        return CompletableFuture.supplyAsync {
            blockingApi.getDetails(id)
        }
    }
}

fun getShowDetailsWithFutures(query: String, tableView: TableView<ShowDetails>) {
    val futureApi: FutureApi = FutureApiImpl()

    futureApi.search(query)
        .thenApply { summaries ->
            val futures = summaries.map { futureApi.getDetails(it.id) }
            Stream.of(*futures.toTypedArray())
                .map(CompletableFuture<ShowDetails>::join)
                .collect(Collectors.toList())
        }
        .thenAccept { results: List<ShowDetails> ->
            Platform.runLater {
                tableView.setData(results)
            }
        }
}
