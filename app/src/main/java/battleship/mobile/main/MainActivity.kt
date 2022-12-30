package battleship.mobile.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import battleship.mobile.DependencyContainer
import battleship.mobile.info.InfoActivity
import battleship.mobile.main.lobby.ui.LobbyViewModel
import battleship.mobile.main.social.domain.SocialViewModel
import battleship.mobile.main.ui.DrawerContent
import battleship.mobile.main.ui.MainScreen
import battleship.mobile.main.ui.MainScreenState
import battleship.mobile.main.ui.MainViewModel
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme
import battleship.mobile.utils.viewModelInit
import kotlinx.coroutines.launch

const val TAG = "BattleshipApp"

class MainActivity : ComponentActivity() {

    val dependencies = (application as DependencyContainer)

    private val mainVM by viewModels<MainViewModel> {
        viewModelInit {
            MainViewModel()
        }
    }
    private val lobbyVM by viewModels<LobbyViewModel> {
        viewModelInit {
            LobbyViewModel()
        }
    }
    private val socialVM by viewModels<SocialViewModel> {
        viewModelInit {
            SocialViewModel()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            BattleshipmobileTheme {
                MainScreen(
                    screen = MainScreenState.LOBBY,
                    onDrawerLobbyRequest = {

                    },
                    onDrawerSocialRequest = {

                    },
                    onDrawerInfoRequest = {
                        InfoActivity.navigate(this)
                    }
                )
            }
        }
    }
}
