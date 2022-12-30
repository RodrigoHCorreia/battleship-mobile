package battleship.mobile.game.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.game.domain.Board
import battleship.mobile.game.domain.Coordinates
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme

class GameScreenState;

@Composable
fun WaitingScreen() {

}

@Composable
fun PlanningScreen() {

}

@Composable
fun FightingScreen(board : Board, enemy : Board) {


}

@Composable
fun FinishedScreen(board : Board, enemy : Board) {

}

@Composable
fun GameScreen(
    state : GameScreenState,
    onBackRequested : () -> Unit,
    onShootRequested : (Coordinates) -> Unit
) {
    val navHandler = NavigationHandlers(onBackRequested = onBackRequested)
    BattleshipmobileTheme {
        Scaffold(
            topBar = { TopBar(navigation = navHandler) }
        ) {
            it.calculateBottomPadding()
            
        }
    }
}

@Preview
@Composable
fun GameScreenWaitingPreview() {
    BattleshipmobileTheme {
        GameScreen(
            state = GameScreenState(),
            onBackRequested = {},
            onShootRequested = {}
        )
    }
}

@Preview
@Composable
fun GameScreenPlanningPreview() {
    BattleshipmobileTheme {
        GameScreen(
            state = GameScreenState(),
            onBackRequested = {},
            onShootRequested = {}
        )
    }
}

@Preview
@Composable
fun GameScreenFightingPreview() {
    BattleshipmobileTheme {
        GameScreen(
            state = GameScreenState(),
            onBackRequested = {},
            onShootRequested = {}
        )
    }
}

@Preview
@Composable
fun GameScreenFinishedPreview() {
    BattleshipmobileTheme {
        GameScreen(
            state = GameScreenState(),
            onBackRequested = {},
            onShootRequested = {}
        )
    }
}


