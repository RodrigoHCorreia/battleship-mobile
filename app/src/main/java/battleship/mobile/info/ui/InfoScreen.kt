package battleship.mobile.info.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.info.domain.AppAuthor
import battleship.mobile.info.domain.AppInfo
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

const val InfoScreenTag = "InfoScreen"

data class ServerInfoState(
    val serverInfo: ServerInfo = ServerInfo("x.y.z", emptyList()),
    val isLoading: RefreshingState = RefreshingState.Idle
)

@Composable
fun InfoScreen(
    appInfo: AppInfo,
    state: ServerInfoState = ServerInfoState(),
    onSendEmailRequested: () -> Unit = { },
    onBackRequested : () -> Unit
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
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Battleship",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "version: " + appInfo.version,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = "API",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
                if (state.isLoading == RefreshingState.Refreshing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 3.dp
                    )
                }
                else {
                    Text(
                        text = "version: " + state.serverInfo.version,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = "Authors",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = appInfo.authors.joinToString(separator = "\n") { it.id.toString() + " - " + it.name },
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )
                TextButton(onClick = onSendEmailRequested) {
                    Icon(Icons.Default.Email, contentDescription = "Localized description")
                    Text(
                        text = "Contact",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun InfoScreenPreview() {
    BattleshipMobileTheme {
        InfoScreen(
            appInfo = AppInfo("x.y.z", listOf(
                AppAuthor(
                    123,
                    "John Doe",
                    "johndoe@google.com"
                )
            )),
            onBackRequested = {}
        )
    }
}