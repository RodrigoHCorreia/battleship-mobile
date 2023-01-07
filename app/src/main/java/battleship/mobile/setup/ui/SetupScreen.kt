package battleship.mobile.setup.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

@Composable
fun SetupScreen(
    onBackRequested : () -> Unit
) {
    val navHandler = NavigationHandlers(onBackRequested = onBackRequested)
    Scaffold(
        topBar = { TopBar(navigation = navHandler)}
    ) {
        it.calculateBottomPadding() // Fuck linters


    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    BattleshipMobileTheme {
        SetupScreen(onBackRequested = {})
    }
}