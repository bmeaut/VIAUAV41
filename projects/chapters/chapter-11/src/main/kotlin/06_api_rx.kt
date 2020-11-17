import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javafx.scene.control.TableView

interface RxApi {
    fun search(query: String): Single<List<JobSummary>>
    fun getDetails(jobId: String): Single<JobDetails>
}

class RxApiImpl : RxApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String): Single<List<JobSummary>> {
        return Single.create<List<JobSummary>> { emitter ->
            emitter.onSuccess(blockingApi.search(query))
        }
    }

    override fun getDetails(jobId: String): Single<JobDetails> {
        return Single.create<JobDetails> { emitter ->
            emitter.onSuccess(blockingApi.getDetails(jobId))
        }
    }
}

fun getJobDetailsWithRx(query: String, tableView: TableView<JobDetails>) {
    val rxApi: RxApi = RxApiImpl()

    rxApi.search(query)
        .flatMapObservable { jobSummaries ->
            Observable.fromIterable(jobSummaries)
        }
        .flatMapSingle { rxApi.getDetails(it.id) }
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(JavaFxScheduler.platform())
        .subscribe { results: List<JobDetails> ->
            tableView.setData(results)
        }
}

