package battleship.mobile.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.theme.BattleshipmobileTheme
import battleship.mobile.R

@Composable
fun TopBar(
    onBackRequested: (() -> Unit)? = null,
    onRankingRequested: (() -> Unit)? = null,
    onAboutRequested: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (onBackRequested != null) {
                IconButton(onClick = onBackRequested) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            if (onRankingRequested != null) {
                IconButton(onClick = onRankingRequested) {
                    Icon(Icons.Default.List, contentDescription = "Localized description")
                }
            }
            if (onAboutRequested != null) {
                IconButton(onClick = onAboutRequested) {
                    Icon(Icons.Default.Info, contentDescription = "Localized description")
                }
            }
        }
    )}

@Preview
@Composable
private fun TopBarPreviewAboutAndRanking() {
    BattleshipmobileTheme() {
        TopBar(onAboutRequested = { }, onRankingRequested = { })
    }
}

@Preview
@Composable
private fun TopBarPreviewBackAndAbout() {
    BattleshipmobileTheme {
        TopBar(onBackRequested = { }, onAboutRequested = { })
    }
}

@Preview
@Composable
private fun TopBarPreviewBack() {
    BattleshipmobileTheme {
        TopBar(onBackRequested = { })
    }
}
