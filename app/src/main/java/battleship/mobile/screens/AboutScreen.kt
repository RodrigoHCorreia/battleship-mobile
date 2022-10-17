import androidx.compose.runtime.Composable
import battleship.mobile.FakeServerInfoService
import battleship.mobile.data.Info
import battleship.mobile.data.appInfo

@Composable
fun AboutScreen(
    appInfo: Info,
    serverInfo: Info? = null,

) {
    AboutView(appInfo)
    //TODO: serverInfo
    AboutView(serverInfo)
}
