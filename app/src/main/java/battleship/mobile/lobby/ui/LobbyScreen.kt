package battleship.mobile.lobby.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import battleship.mobile.lobby.domain.ActiveGame
import battleship.mobile.ui.*
import battleship.mobile.ui.theme.BattleshipMobileTheme
import kotlinx.coroutines.launch

const val LobbyScreenTag = "LobbyScreen"

data class LobbyScreenState(
    val activeGames : List<ActiveGame> = emptyList(),
    val isLoading : RefreshingState = RefreshingState.Idle
)

@Composable
fun LobbyScreen(
    state : LobbyScreenState = LobbyScreenState(),
    onDrawerSocialRequest : () -> Unit,
    onDrawerInfoRequest : () -> Unit,
    onDrawerLogoutRequest : () -> Unit,
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

    BattleshipMobileTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag(LobbyScreenTag),
            scaffoldState = scaffoldState,
            topBar = { TopBar(navHandlers) },
            drawerContent = {
                DrawerContent(
                    onLobbyClick = { scope.launch { scaffoldState.drawerState.close() } },
                    onSocialClick = onDrawerSocialRequest,
                    onInfoClick = onDrawerInfoRequest,
                    onLogoutClick = onDrawerLogoutRequest
                )
            },
            drawerBackgroundColor = MaterialTheme.colors.background

        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Battleship",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 60.dp, bottom = 30.dp)
                )

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    AppButton(text = "Match", onClick = onMatchRequested)
                    //AppButton(text = "Host", onClick = onHostRequested)
                }

                Text(
                    text = "Active Games",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(top = 50.dp)
                )
                if (state.isLoading == RefreshingState.Refreshing) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 40.dp),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 5.dp
                    )
                }
                else {
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        elevation = 5.dp,
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        LazyColumn {
                            items(state.activeGames) { activeGame ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            // TODO("Not yet implemented")
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Game " + activeGame.id,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colors.background,
                                        modifier = Modifier.padding(20.dp)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.padding(20.dp)
                                    ) {
                                        Text(
                                            text = activeGame.player,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 18.sp,
                                            color = MaterialTheme.colors.background
                                        )
                                        Icon(
                                            Icons.Default.PlayArrow,
                                            "Play",
                                            tint = MaterialTheme.colors.background
                                        )
                                    }

                                }

                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LobbyScreenPreview() {
    BattleshipMobileTheme {
        LobbyScreen(
            state = LobbyScreenState(listOf(
                ActiveGame(1, "goncaloaps"),
                ActiveGame(2, "admorgado"),
                ActiveGame(3, "rodrigohcorreia")
            ), RefreshingState.Idle),
            onDrawerSocialRequest = {},
            onDrawerInfoRequest = {},
            onDrawerLogoutRequest = {},
            onMatchRequested = {},
            onHostRequested = {}
        )
    }
}
