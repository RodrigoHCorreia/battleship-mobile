package battleship.mobile.main.lobby.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import battleship.mobile.ui.AppButton

@Composable
fun ActionView() {

}

@Composable
fun LobbyActionsView(
    onMatchRequested : () -> Unit,
    onHostRequested : () -> Unit,
) {
    Row {
        AppButton(text = "Match", onMatchRequested)
        AppButton(text = "Host", onHostRequested)
    }
}

@Composable
fun GameListView(
    onGameRequested : () -> Unit
) {
    Column {

    }
}

@Composable
fun LobbyScreen(
    onMatchRequested: () -> Unit,
    onHostRequested : () -> Unit,
    onGameRequested : () -> Unit
) {
    LobbyActionsView(onMatchRequested, onHostRequested)

    Divider()

    GameListView(onGameRequested)
}


