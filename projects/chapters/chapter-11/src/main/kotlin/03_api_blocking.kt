import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javafx.scene.control.TableView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsApi {
    @GET("search/shows")
    fun getShows(@Query("q") query: String): Call<List<ShowResponse>>

    @GET("shows/{id}")
    fun getShowDetails(@Path("id") id: Int): Call<ShowDetails>
}

val tvShowsApi: TvShowsApi
    get() {
        val shows = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
        val api = shows.create<TvShowsApi>()
        return api
    }

interface BlockingApi {
    fun search(query: String): List<ShowSummary>
    fun getDetails(id: Int): ShowDetails
}

class BlockingApiImpl : BlockingApi {
    private val api = tvShowsApi

    override fun search(query: String): List<ShowSummary> {
        Thread.sleep(200L)
        return api.getShows(query).execute().body()!!.map(ShowResponse::show)
    }

    override fun getDetails(id: Int): ShowDetails {
        Thread.sleep(200L)
        return api.getShowDetails(id).execute().body()!!
    }
}

fun getShowDetailsBlocking(query: String, tableView: TableView<ShowDetails>) {
    val api: BlockingApi = BlockingApiImpl()

    val showSummaries = api.search(query) // blocking network call
    val details = showSummaries.map { summary ->
        api.getDetails(summary.id) // blocking network calls
    }

    tableView.setData(details)
}
