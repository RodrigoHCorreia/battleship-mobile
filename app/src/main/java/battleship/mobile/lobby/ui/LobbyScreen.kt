package battleship.mobile.lobby.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import battleship.mobile.ui.DrawerContent
import battleship.mobile.ui.AppButton
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
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

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = { TopBar(navHandlers) },
        drawerContent = {
            DrawerContent(
                onLobbyClick = { scope.launch { scaffoldState.drawerState.close() } },
                onSocialClick = onDrawerSocialRequest,
                onInfoClick = onDrawerInfoRequest
            )
        }

    ) {
        // TODO i hate linters, this line is to make it shut the fuck up
        it.calculateBottomPadding()

        Row {
            AppButton(text = "Match", onMatchRequested)
            AppButton(text = "Host", onHostRequested)
        }
    }
}
