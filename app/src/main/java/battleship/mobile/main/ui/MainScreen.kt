package battleship.mobile.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import battleship.mobile.main.lobby.ui.LobbyScreen
import battleship.mobile.main.social.ui.SocialScreen
import battleship.mobile.ui.DrawerContent
import battleship.mobile.ui.DrawerItem
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme
import kotlinx.coroutines.launch

enum class Screen {
    LOBBY, SOCIAL
}


fun buildItemList(
    onDrawerScreenRequested : (Screen) -> Unit,
    onDrawerInfoRequested : () -> Unit
) =
    listOf(
        DrawerItem(
            icon = Icons.Default.Home,
            title = "Lobby",
            description = "Lobby Screen",
            onClick = { onDrawerScreenRequested(Screen.LOBBY) },
            tag = "tag" // TODO
        ),
        DrawerItem(
            icon = Icons.Default.Star,
            title = "Social",
            description = "Social Screen",
            onClick = { onDrawerScreenRequested(Screen.SOCIAL) },
            tag = "tag" //TODO
        ),
        DrawerItem(
            icon = Icons.Default.Info,
            title = "Info",
            description = "Info Screen",
            onClick = onDrawerInfoRequested,
            tag = "tag" // TODO
        ),
    )

@Composable
fun MainScreen(
    screen : Screen,
    onDrawerRequested : () -> Unit,
    onDrawerScreenRequested : (Screen) -> Unit,
    onDrawerInfoRequested : () -> Unit,
    scaffoldState : ScaffoldState
) {
    val drawerItems = buildItemList(
        onDrawerScreenRequested,
        onDrawerInfoRequested
    )

    val navHandler = NavigationHandlers(onDrawerRequested = onDrawerRequested)
    BattleshipMobileTheme {
        Scaffold(
            topBar = { TopBar(navigation = navHandler) },
            scaffoldState = scaffoldState,
            drawerContent = {
                DrawerContent( drawerItems )
            },
            drawerBackgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                when(screen) {
                    Screen.LOBBY -> LobbyScreen()
                    Screen.SOCIAL -> SocialScreen()
                }
            }
        }
    }
}



