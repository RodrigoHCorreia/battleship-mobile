package battleship.mobile.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import battleship.mobile.DependencyContainer
import battleship.mobile.info.InfoActivity
import battleship.mobile.main.lobby.ui.LobbyViewModel
import battleship.mobile.main.social.ui.SocialViewModel
import battleship.mobile.main.ui.MainScreen
import battleship.mobile.main.ui.MainViewModel
import battleship.mobile.utils.viewModelInit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val dependencies by lazy { application as DependencyContainer }

    private val mainViewModel by viewModels<MainViewModel> {
        viewModelInit { MainViewModel() }
    }

    private val lobbyViewModel by viewModels<LobbyViewModel> {
        viewModelInit { LobbyViewModel(dependencies.lobby) }
    }


    private val socialViewModel by viewModels<SocialViewModel> {
        viewModelInit { SocialViewModel(dependencies.social) }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()

            val screen by mainViewModel.screen.collectAsState()

            MainScreen(
                screen = screen,
                onDrawerRequested = {
                    scope.launch { scaffoldState.drawerState.open() }
                },
                onDrawerInfoRequested = {
                    InfoActivity.navigate(this)
                },
                onDrawerScreenRequested = {
                    mainViewModel.setScreen(it)
                    scope.launch { scaffoldState.drawerState.close() }
                },
                scaffoldState = scaffoldState
            )
        }
    }

}