package battleship.mobile.lobby.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.ui.DrawerContent
import battleship.mobile.ui.AppButton
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme
import kotlinx.coroutines.launch

@Composable
fun LobbyScreen(
    onDrawerSocialRequest : () -> Unit,
    onDrawerInfoRequest : () -> Unit,
    onMatchRequested : () -> Unit,
    onHostRequested : () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navHandlers = NavigationHandlers(
        onDrawerRequested = {
            scope.launch { scaffoldState.drawerState.open() }
        }
    )

    BattleshipmobileTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(navHandlers) },
            drawerContent = {
                DrawerContent(
                    onLobbyClick = { scope.launch { scaffoldState.drawerState.close() } },
                    onSocialClick = onDrawerSocialRequest,
                    onInfoClick = onDrawerInfoRequest
                )
            },
            drawerBackgroundColor = MaterialTheme.colors.primary

        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = androidx.compose.ui.Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Battleships",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colors.primary
                )
                AppButton(text = "Match", onMatchRequested)
                AppButton(text = "Host", onHostRequested)
            }
        }
    }
}

@Preview
@Composable
fun LobbyScreenPreview() {
    BattleshipmobileTheme {
        LobbyScreen(
            onDrawerSocialRequest = {},
            onDrawerInfoRequest = {},
            onMatchRequested = {},
            onHostRequested = {}
        )
    }
}
