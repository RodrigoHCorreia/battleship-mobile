package battleship.mobile.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.data.Rank
import battleship.mobile.ui.RefreshFab
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme


data class RankingScreenState(
    val ranking: List<Rank> = emptyList(),
    val isLoading: RefreshingState = RefreshingState.Idle
)

@Composable
fun RankingScreen(
    state: RankingScreenState = RankingScreenState(),
    onUpdateRequest: () -> Unit = { },
    onBackRequested: () -> Unit = { }
) {
    BattleshipmobileTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag("RankingScreen"),
            backgroundColor = MaterialTheme.colors.background,
            topBar = { TopBar(onBackRequested = onBackRequested) },
            floatingActionButton = {
                RefreshFab(     // maybe replace button for pull to refresh?
                    onClick = onUpdateRequest,
                    state = state.isLoading
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "User",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Rank",
                        fontWeight = FontWeight.Bold
                    )
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(state.ranking) { rank ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = rank.user
                            )
                            Text(
                                text = rank.elo.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun RankingScreenPreview() {
    RankingScreen(RankingScreenState())
}
