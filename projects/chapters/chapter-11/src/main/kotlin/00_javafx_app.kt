import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

data class ShowResponse(
    val score: Double,
    val show: ShowSummary,
)

data class ShowSummary(
    val id: Int,
    val name: String,
)

data class ShowDetails(
    val id: Int,
    val name: String,
    val status: String,
    val runtime: Int?,
    val premiered: String?,
    val language: String?,
)

fun main() {
    Application.launch(ShowsApp::class.java)
}

class ShowsApp : Application() {

    lateinit var tableView: TableView<ShowDetails>
    lateinit var textField: TextField

    override fun start(primaryStage: Stage) {
        val root = VBox()
        val scene = Scene(root)
        primaryStage.scene = scene
        primaryStage.title = "TV Show API Browser"

        tableView = TableView<ShowDetails>()
        tableView.columns.apply {
            add(TableColumn<ShowDetails, String>("ID").apply {
                cellValueFactory = PropertyValueFactory("id")
            })
            add(TableColumn<ShowDetails, String>("Title").apply {
                cellValueFactory = PropertyValueFactory("name")
            })
            add(TableColumn<ShowDetails, String>("Status").apply {
                cellValueFactory = PropertyValueFactory("status")
            })
            add(TableColumn<ShowDetails, String>("Premiered").apply {
                cellValueFactory = PropertyValueFactory("premiered")
            })
            add(TableColumn<ShowDetails, String>("Language").apply {
                cellValueFactory = PropertyValueFactory("language")
            })
            add(TableColumn<ShowDetails, String>("Runtime").apply {
                cellValueFactory = PropertyValueFactory("runtime")
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
        tvShowsApi
    }

    private fun performSearch() {
        getShowDetailsBlocking(textField.text.trim(), tableView)
//        getShowDetailsWithCallbacks(textField.text.trim(), tableView)
//        getShowDetailsWithFutures(textField.text.trim(), tableView)
//        getShowDetailsWithRx(textField.text.trim(), tableView)
//        getShowDetailsCoroutines(textField.text.trim(), tableView)
//        getShowDetailsCoroutinesApi(textField.text.trim(), tableView)
    }

}

fun <T> TableView<T>.setData(data: List<T>) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    items.clear()
    items.addAll(data)
}
