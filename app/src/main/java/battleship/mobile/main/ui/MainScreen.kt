package battleship.mobile.main.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import battleship.mobile.main.lobby.ui.LobbyScreen
import battleship.mobile.main.social.ui.SocialScreen
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import kotlinx.coroutines.launch

enum class MainScreenState {
    LOBBY, SOCIAL
}

@Composable
fun MainScreen(
    screen : MainScreenState,
    onDrawerLobbyRequest : () -> Unit,
    onDrawerSocialRequest : () -> Unit,
    onDrawerInfoRequest : () -> Unit

) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navHandlers = NavigationHandlers(
        onDrawerRequested = {
                scope.launch { scaffoldState.drawerState.open() }
        })

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = { TopBar(navHandlers) },
        drawerContent = {
            DrawerContent(
                onLobbyClick = onDrawerLobbyRequest,
                onSocialClick = onDrawerSocialRequest,
                onInfoClick = onDrawerInfoRequest
            )
        }

    ) {
        // TODO i hate linters, this line is to make it shut the fuck up
        it.calculateBottomPadding()

        when(screen) {
            MainScreenState.LOBBY -> {
                LobbyScreen(
                    onMatchRequested = {},
                    onHostRequested = {},
                    onGameRequested = {}
                )
            }
            MainScreenState.SOCIAL -> {
                SocialScreen()
            }
        }
    }
}
