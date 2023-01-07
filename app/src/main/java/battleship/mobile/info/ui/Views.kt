package battleship.mobile.info.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.theme.BattleshipMobileTheme

@Composable
fun ServerAuthorView() {

}

@Composable
fun AppInfoAuthorView() {

}

@Composable
fun ServerInfoView() {

}

@Composable
fun AppInfoView() {

}

@Preview(showBackground = true)
@Composable
fun ServerAuthorPreview() {
    BattleshipMobileTheme {
        ServerAuthorView()
    }
}

@Preview(showBackground = true)
@Composable
fun AppAuthorPreview() {
    BattleshipMobileTheme {
        AppInfoAuthorView()
    }
}


