import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var count by remember { mutableStateOf(0) }

    MaterialTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$count")

            Row {
                Button(onClick = { count = (count - 1).coerceAtLeast(0) }) {
                    Text("-")
                }
                Button(onClick = { count++ }) {
                    Text("+")
                }
            }
        }
    }
}

fun main() = application {
    Window(
        title = "My Compose Desktop app",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
