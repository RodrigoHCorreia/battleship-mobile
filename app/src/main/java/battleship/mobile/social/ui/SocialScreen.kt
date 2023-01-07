package battleship.mobile.social.ui

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import battleship.mobile.social.domain.User
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.RefreshFab
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipMobileTheme

const val SocialScreenTag = "SocialScreen"

data class SocialScreenState(
    val ranking: List<User> = emptyList(),
    val isLoading: RefreshingState = RefreshingState.Idle
)

@Composable
fun SocialScreen(
    state: SocialScreenState = SocialScreenState(),
    onUpdateRequest: () -> Unit = { },
    onBackRequested: () -> Unit = { }
) {
    val navHandler = NavigationHandlers(
        onBackRequested = onBackRequested
    )

    BattleshipMobileTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .testTag(SocialScreenTag),
            topBar = { TopBar(navigation = navHandler) },
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
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = "Rank",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.ranking) { user ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = user.username,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.primary
                            )
                            Text(
                                text = user.elo.toString(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
    }
}


