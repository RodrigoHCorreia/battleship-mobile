package battleship.mobile.info.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme


@Composable
fun InfoScreen(
    onBackRequested : () -> Unit
) {
    val navHandler = NavigationHandlers(
        onBackRequested = onBackRequested
    )

    BattleshipmobileTheme {
        Scaffold(
            topBar = { TopBar(navigation = navHandler) }
        ) {
            it.calculateBottomPadding() // Fuck linters
        }
    }

}

@Preview
@Composable
fun InfoScreenPreview() {
    BattleshipmobileTheme {
        InfoScreen(
            onBackRequested = {}
        )
    }
}