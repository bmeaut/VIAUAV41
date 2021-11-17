import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javafx.scene.control.TableView

interface RxApi {
    fun search(query: String): Single<List<ShowSummary>>
    fun getDetails(id: Int): Single<ShowDetails>
}

class RxApiImpl : RxApi {
    private val blockingApi: BlockingApi = BlockingApiImpl()

    override fun search(query: String): Single<List<ShowSummary>> {
        return Single.create<List<ShowSummary>> { emitter ->
            emitter.onSuccess(blockingApi.search(query))
        }
    }

    override fun getDetails(id: Int): Single<ShowDetails> {
        return Single.create<ShowDetails> { emitter ->
            emitter.onSuccess(blockingApi.getDetails(id))
        }
    }
}

fun getShowDetailsWithRx(query: String, tableView: TableView<ShowDetails>) {
    val rxApi: RxApi = RxApiImpl()

    rxApi.search(query)
        .flatMapObservable { showSummaries ->
            Observable.fromIterable(showSummaries)
        }
        .flatMapSingle { rxApi.getDetails(it.id) }
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(JavaFxScheduler.platform())
        .subscribe { results: List<ShowDetails> ->
            tableView.setData(results)
        }
}

