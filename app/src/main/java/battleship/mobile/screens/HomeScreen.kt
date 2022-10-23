package battleship.mobile.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.ui.theme.BattleshipmobileTheme

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
                Button(
                    onClick = { onMMRequest() },
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) { Text("Start Game") }
                Button(
                    onClick = { onRankingRequested() },
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) { Text("Ranking") }
                Button(
                    onClick = { onLogoutRequested() },
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) { Text("Logout") }
                TextButton(
                    onClick = { onAboutRequest() },
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Localized description")
                    Text("Info")
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