import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

data class JobSummary(
    val id: String,
    val title: String
)

data class JobDetails(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val description: String,
    val type: String
)

fun main() {
    Application.launch(JobApp::class.java)
}

class JobApp : Application() {

    lateinit var tableView: TableView<JobDetails>
    lateinit var textField: TextField

    override fun start(primaryStage: Stage) {
        val root = VBox()
        val scene = Scene(root)
        primaryStage.scene = scene
        primaryStage.title = "GitHub Job Browser"

        tableView = TableView<JobDetails>()
        tableView.columns.apply {
            add(TableColumn<JobDetails, String>("ID").apply {
                cellValueFactory = PropertyValueFactory<JobDetails, String>("id")
            })
            add(TableColumn<JobDetails, String>("Title").apply {
                cellValueFactory = PropertyValueFactory<JobDetails, String>("title")
            })
            add(TableColumn<JobDetails, String>("Company").apply {
                cellValueFactory = PropertyValueFactory<JobDetails, String>("company")
            })
            add(TableColumn<JobDetails, String>("Location").apply {
                cellValueFactory = PropertyValueFactory<JobDetails, String>("location")
            })
        }
        root.children.add(tableView)

        val bottomLayout = HBox()
        textField = TextField()
        val button = Button().apply {
            text = "Search"
            setOnAction {
                performSearch()
            }
        }
        bottomLayout.children.apply {
            add(textField)
            add(button)
            add(ProgressBar())
        }

        root.children.add(bottomLayout)

        primaryStage.show()

        // Dummy call to create API instance
        gitHubJobsApi
    }

    private fun performSearch() {
        getJobDetailsBlocking(textField.text.trim(), tableView)
//        getJobDetailsWithCallbacks(textField.text.trim(), tableView)
//        getJobDetailsWithFutures(textField.text.trim(), tableView)
//        getJobDetailsWithRx(textField.text.trim(), tableView)
//        getJobDetailsCoroutines(textField.text.trim(), tableView)
//        getJobDetailsCoroutinesApi(textField.text.trim(), tableView)
    }

}

fun <T> TableView<T>.setData(data: List<T>) {
    require(Platform.isFxApplicationThread())
    items.clear()
    items.addAll(data)
}
