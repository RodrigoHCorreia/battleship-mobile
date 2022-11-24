package battleship.mobile.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.screens.utils.AppDefaultButton
import battleship.mobile.ui.theme.BattleshipmobileTheme

private const val START_TEXT = "Start Game"
private const val INFO_TEXT = "Info"
private const val RANKING_TEXT = "Ranking"
private const val LOGOUT_TEXT = "Logout"

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    onMMRequest: () -> Unit = { },
    onRankingRequested: () -> Unit = { },
    onLogoutRequested: () -> Unit = { },
    onAboutRequest: () -> Unit = { }
) {
    BattleshipmobileTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Battleships",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                AppDefaultButton(onClick = onMMRequest, text = START_TEXT)
                AppDefaultButton(onClick = onRankingRequested, text = RANKING_TEXT)
                AppDefaultButton(onClick = onLogoutRequested, text = LOGOUT_TEXT)
                TextButton(
                    onClick = { onAboutRequest() },
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Localized description")
                    Text(INFO_TEXT)
                }
            }

        }
    }
}
/*
@Composable
fun TopBar(onAboutRequest: (() -> Unit)?, onRankingRequest: (() -> Unit)?, onBackRequested: (() -> Unit)?) {
    //TODO: TopBar with dropdown
}
*/
@Composable
fun MatchMaking(onClick: () -> Unit) {
    // TODO: MatchMaking Screen
}