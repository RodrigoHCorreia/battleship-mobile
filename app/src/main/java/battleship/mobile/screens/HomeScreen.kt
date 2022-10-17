package battleship.mobile.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import battleship.mobile.TAG
import battleship.mobile.ui.theme.BattleshipmobileTheme

@Composable
fun HomeScreen(
    onAboutRequest: (() -> Unit)? = null,
    onBackRequested: (() -> Unit)? = null,
    onRankingRequested: (() -> Unit)? = null,
    onMMRequest: (() -> Unit)? = null,
) {
    Log.i(TAG, "HomeScreen: composing")
    BattleshipmobileTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButton = {
                if (onMMRequest != null) {
                    MatchMaking(
                        onClick = onMMRequest,
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            topBar = {
                TopBar(
                    onAboutRequest = onAboutRequest,
                    onRankingRequest = onRankingRequested,
                    onBackRequested = onBackRequested
                )
            }
        ) {    TODO()      }
    }
}

@Composable
fun TopBar(onAboutRequest: (() -> Unit)?, onRankingRequest: (() -> Unit)?, onBackRequested: (() -> Unit)?) {
    //TODO: TopBar with dropdown
}

@Composable
fun MatchMaking(onClick: () -> Unit) {
    // TODO: MatchMaking Screen
}