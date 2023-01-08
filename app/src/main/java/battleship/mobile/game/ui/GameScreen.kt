package battleship.mobile.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.R
import battleship.mobile.game.domain.*
import battleship.mobile.ui.AppButton
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

@Composable
fun LoadingScreen(tip : String = "") {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Loading!", style = MaterialTheme.typography.h2)
        CircularProgressIndicator()
        TipView(tip)
    }
}


@Composable
fun WaitingScreen(
    game : GameWaiting,
    tip : String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Waiting", style = MaterialTheme.typography.h2)

            Text("Game ID: ${game.id}", style = MaterialTheme.typography.subtitle2)
        }
        LinearProgressIndicator()
        TipView(tip = tip)
    }
}

@Composable
fun PlanningScreen(
    game : GamePlanning,
    onPlaceRequested : () -> Unit,
) {
    Column {
    }
}

@Composable
fun FightingScreen(
    game : GameFight,
    onShootRequested: (Coordinates) -> Unit
) {
    //TODO perhaps make this colum scrollable
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Player Board
        BoardView(game.board)

        Divider()

        // Enemy Board
        BoardView(game.enemyBoard, onShootRequested)
    }
}

@Composable
fun FinishedScreen(
    game : GameFinished
) {
    Column {

    }
}

@Composable
fun GameScreen(
    game : Game?,
    tip : String = "",
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
                null -> LoadingScreen(tip)
                is GameWaiting -> WaitingScreen(game, tip)
                is GamePlanning -> PlanningScreen(
                    game = game,
                    onPlaceRequested = { TODO() }
                )
                is GameFight -> FightingScreen(
                    game = game,
                    onShootRequested = onShootRequested
                )
                is GameFinished -> FinishedScreen(game = game)
            }
        }
    }
}

@Preview
@Composable
fun GameScreenLoadingPreview() {
    BattleshipMobileTheme {
        GameScreen(
            game = null,
            tip = stringArrayResource(id = R.array.tips).random()
        )
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
            tip = stringArrayResource(id = R.array.tips).random()
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
    BattleshipMobileTheme {
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



