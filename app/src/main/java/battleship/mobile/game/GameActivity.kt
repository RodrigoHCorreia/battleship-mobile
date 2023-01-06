package battleship.mobile.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import battleship.mobile.BattleshipApplication
import battleship.mobile.DependencyContainer
import battleship.mobile.TAG
import battleship.mobile.game.ui.GameScreen
import battleship.mobile.game.ui.GameViewModel
import battleship.mobile.utils.viewModelInit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameActivity : ComponentActivity() {


    private val viewModel by viewModels<GameViewModel> {
        val dependencies = (application as DependencyContainer)
        viewModelInit {
            GameViewModel(dependencies.game)
        }
    }

    companion object {
        fun navigate(origin : Context) {
            with(origin) {
                startActivity(
                    Intent(this, GameActivity::class.java).also {
                        //TODO add game details
                    }
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            val uiState by viewModel.uiState.collectAsState() // TODO We are using by instead of =, is that good?
            GameScreen(
                state = uiState,
                onBackRequested = { finish() },
                onShootRequested = { Log.v(TAG, "$it") }
            )
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                }
            }
        }
    }
}

