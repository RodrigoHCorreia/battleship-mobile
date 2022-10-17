import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.Author
import battleship.mobile.data.Info
import battleship.mobile.data.appInfo

@Composable
fun versionView(version: String) {

    Text(
        text = version, style = MaterialTheme.typography.h5
    )

}

@Composable
fun authorView(author: Author) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "${author.id} - ${author.name}", style = MaterialTheme.typography.h5
        )

        Button(
            onClick = {
                val link = "mailto:${author.email}"
                TODO("learn intent first")
            },
        ) {
            Text(text = "Email")
        }
    }


}

@Composable
fun AboutView(info: Info?) {
    if (info == null) return
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Text("Authors:")
        info.authors.forEach {
            authorView(it)
        }

        Text("Version Info:")
        versionView(info.version)
    }

}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    val info = appInfo
    AboutView(info = info)
}

