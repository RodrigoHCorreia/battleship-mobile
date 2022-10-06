import android.icu.text.IDNA.Info
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.Author
import battleship.mobile.ServerInfo
import battleship.mobile.data.AppInfo
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
fun AppInfoView(appInfo: AppInfo) {
    Text(text= "App Info", style = MaterialTheme.typography.h4)
    Text("Authors:")
    appInfo.authors.forEach {
        authorView(it)
    }

    Text("Version Info:")
    versionView(appInfo.version)
}

@Composable
fun ServerInfoView(serverInfo : ServerInfo) {
    Text(text= "Server Info", style = MaterialTheme.typography.h4)
    Text("Authors:")
    serverInfo.authors.forEach {
        authorView(it)
    }

    Text("Version Info:")
    versionView(serverInfo.version)
}

@Composable
fun AboutView(appInfo: AppInfo, serverInfo : ServerInfo?) {
    Column {
        AppInfoView(appInfo = appInfo);
        serverInfo?.let {
            ServerInfoView(serverInfo = it);
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    val info = appInfo
    AboutView(
        appInfo = info,
        serverInfo = null
    )
}

