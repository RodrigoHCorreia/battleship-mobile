package battleship.mobile.lobby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import battleship.mobile.info.InfoActivity
import battleship.mobile.lobby.ui.LobbyScreen
import battleship.mobile.social.SocialActivity
import battleship.mobile.ui.theme.BattleshipmobileTheme

const val TAG = "BattleshipApp"

class LobbyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BattleshipmobileTheme {
                LobbyScreen(
                    onDrawerSocialRequest = {
                        SocialActivity.navigate(this)
                    },
                    onDrawerInfoRequest = {
                       InfoActivity.navigate(this)
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
