package battleship.mobile.info.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.info.domain.AppAuthor
import battleship.mobile.info.domain.AppInfo
import battleship.mobile.info.domain.ServerAuthor
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

const val InfoScreenTag = "InfoScreen"

@Composable
fun InfoScreen(
    appInfo: AppInfo,
    serverInfo: ServerInfo? = null,
    onSendEmailRequested: () -> Unit = { },
    onBackRequested: () -> Unit
) {
    val navHandler = NavigationHandlers(
        onBackRequested = onBackRequested
    )
    BattleshipMobileTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag(InfoScreenTag),
            topBar = { TopBar(navigation = navHandler) },
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                AppInfoView(appInfo)
                ServerInfoView(serverInfo)
                SendEmailButton(onSendEmailRequested)
            }
        }
    }

}

@Preview
@Composable
fun InfoScreenWithServerInfoPreview() {
    BattleshipMobileTheme {
        InfoScreen(
            appInfo = AppInfo(
                "x.y.z", listOf(
                    AppAuthor(
                        123,
                        "John Doe",
                        "johndoe@google.com"
                    )
                )
            ),
            serverInfo = ServerInfo("0.0.1", listOf(ServerAuthor(1, "John Server Dev", "johnServerDev@gmail.com"))),
            onBackRequested = {}
        )
    }
}

@Preview
@Composable
fun InfoScreenWithoutServerInfoPreview() {
    BattleshipMobileTheme {
        InfoScreen(
            appInfo = AppInfo(
                "x.y.z", listOf(
                    AppAuthor(
                        123,
                        "John Doe",
                        "johndoe@google.com"
                    )
                )
            ),
            onBackRequested = {}
        )
    }
}