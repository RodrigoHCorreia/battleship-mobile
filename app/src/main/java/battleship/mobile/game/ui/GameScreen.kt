package battleship.mobile.game.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.game.domain.*
import battleship.mobile.ui.AppButton
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

@Composable
fun LoadingScreen() {
    Text("Refreshing!") // TODO: make a pretty refreshing/loading screen
}


@Composable
fun WaitingScreen(
    game : GameWaiting
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Waiting!",
        ) // TODO: Add H1 style
        Text(
            text = "Game ID: ${55555}" //TODO(put actual game id here)
        ) // TODO: Add Grey subtitle (as if it were debug text!"

    }
}

@Composable
fun PlanningScreen(
    game : GamePlanning,
    onPlaceRequested : () -> Unit,
) {
    Column {

        AppButton(text = "Submit") {
            onPlaceRequested()
        }
    }
}

@Composable
fun FightingScreen(
    game : GameFight,
    onShootRequested: (Coordinates) -> Unit
) {
    Column {
        // Player Board
        BoardView(game.board)

        Divider()

        // Enemy Board
        BoardView(game.enemyBoard, onShootRequested)
    }
}

@Composable
fun FinishedScreen(board : Board, enemy : Board) {
    Column {

    }
}

@Composable
fun GameScreen(
    game : Game?,
    onBackRequested : () -> Unit = { },
    onPlaceRequested : () -> Unit = { },
    onForfeitRequested: () -> Unit = { }, // This is not implemented yet!
    onShootRequested : (Coordinates) -> Unit = {}
) {
    val navHandler = NavigationHandlers(onBackRequested = onBackRequested)
    BattleshipMobileTheme {
        Scaffold(
            topBar = { TopBar(navigation = navHandler) }
        ) {
            it.calculateBottomPadding()
            when(game) {
                null -> LoadingScreen()
                is GameWaiting -> WaitingScreen(game)
                is GamePlanning -> PlanningScreen(
                    game = game,
                    onPlaceRequested = { TODO() }
                )
                is GameFight -> FightingScreen(
                    game = game,
                    onShootRequested = onShootRequested
                )
            }
        }
    }
}


@Preview
@Composable
fun GameScreenWaitingPreview() {
    BattleshipMobileTheme {
        GameScreen(
            game = GameWaiting(
                id = 100,
                ranked = true
            ),
        )
    }
}

@Preview
@Composable
fun GameScreenPlanningPreview() {
    BattleshipMobileTheme {
        GameScreen(
            game = GamePlanning(
                id = 52,
                ranked = true,
                board = BoardProposal()
            ),
        )
    }
}

@Preview
@Composable
fun GameScreenFightingPreview() {
    BattleshipmobileTheme {
        GameScreen(
            game = GameFight(
                id = 100,
                ranked = true,
                board = Board(List(10) { List(10)  { Cell.EMPTY } }),
                enemyBoard = Board(List(10) { List(10)  { Cell.EMPTY } }),
                yourTurn = true
            ),
        )
    }
}



