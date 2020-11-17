import javafx.scene.control.TableView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubJobsApi {
    @GET("positions.json")
    fun getPositions(@Query("description") query: String): Call<List<JobSummary>>

    @GET("positions/{id}.json")
    fun getPositionDetails(@Path("id") id: String): Call<JobDetails>
}

val gitHubJobsApi: GitHubJobsApi
    get() {
        val jobs = Retrofit.Builder()
            .baseUrl("https://jobs.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val api = jobs.create<GitHubJobsApi>()
        return api
    }

interface BlockingApi {
    fun search(query: String): List<JobSummary>
    fun getDetails(jobId: String): JobDetails
}

class BlockingApiImpl : BlockingApi {
    private val api = gitHubJobsApi

    override fun search(query: String): List<JobSummary> {
        return api.getPositions(query).execute().body()!!
    }

    override fun getDetails(jobId: String): JobDetails {
        return api.getPositionDetails(jobId).execute().body()!!
    }
}

fun getJobDetailsBlocking(query: String, tableView: TableView<JobDetails>) {
    val api: BlockingApi = BlockingApiImpl()

    val jobSummaries = api.search(query) // blocking network call
    val details = jobSummaries.map { summary ->
        api.getDetails(summary.id) // blocking network calls
    }

    tableView.setData(details)
}
