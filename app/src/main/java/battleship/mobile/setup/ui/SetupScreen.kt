package battleship.mobile.setup.ui

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.setup.domain.ShipPrototype
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme



@Composable
fun ShipConfigView(ship : ShipPrototype) {

}

@Preview
@Composable
fun ShipConfigPreview() {
    BattleshipmobileTheme {
        ShipConfigView(ship = ShipPrototype(
            name = "",
            size = 1,
            quantity = 5,
        ))
    }
}

@Composable
fun BoardDimensionPickerView() {

}

@Composable
fun GameSettingsView() {
    Text(
        text = "Game Settings"
    )
}

@Composable
fun FleetCompositionView() {
    Text(
        text = "Fleet Composition"
    )
}

@Composable
fun SetupScreen(
    onBackRequested : () -> Unit
) {
    val navHandler = NavigationHandlers(onBackRequested = onBackRequested)
    Scaffold(
        topBar = { TopBar(navigation = navHandler) }
    ) {
        it.calculateBottomPadding() // Fuck linters
        Column {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false)
            ) {
                GameSettingsView()
                Divider()
                FleetCompositionView()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    BattleshipMobileTheme {
        SetupScreen(onBackRequested = {})
    }
}