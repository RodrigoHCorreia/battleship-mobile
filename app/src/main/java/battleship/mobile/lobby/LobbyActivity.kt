package battleship.mobile.lobby

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import battleship.mobile.FakeLobby
import battleship.mobile.info.InfoActivity
import battleship.mobile.lobby.ui.LobbyScreen
import battleship.mobile.lobby.ui.LobbyScreenState
import battleship.mobile.lobby.ui.LobbyViewModel
import battleship.mobile.social.SocialActivity
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.theme.BattleshipMobileTheme
import battleship.mobile.utils.viewModelInit

const val TAG = "BattleshipApp"

class LobbyActivity : ComponentActivity() {

    val lobby = FakeLobby()

    private val viewModel by viewModels<LobbyViewModel> {
        viewModelInit {
            LobbyViewModel(lobby)
        }
    }

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, LobbyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (viewModel.activeGames == null)
                viewModel.getActiveGames()

            val loadingState =
                if (viewModel.isLoading) RefreshingState.Refreshing
                else RefreshingState.Idle

            val activeGames = viewModel.activeGames?.getOrNull() ?: emptyList()

            BattleshipMobileTheme {
                LobbyScreen(
                    state = LobbyScreenState(activeGames, loadingState),
                    onDrawerSocialRequest = {
                        SocialActivity.navigate(this)
                    },
                    onDrawerInfoRequest = {
                        InfoActivity.navigate(this)
                    },
                    onDrawerLogoutRequest = {
                        finish()
                    },
                    onMatchRequested = {

                    },
                    onHostRequested = {

                    }
                )
            }
        }
    }

}
