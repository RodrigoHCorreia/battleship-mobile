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
import battleship.mobile.DependencyContainer
import battleship.mobile.TAG
import battleship.mobile.game.domain.GameFight
import battleship.mobile.game.domain.GamePlanning
import battleship.mobile.game.domain.GameWaiting
import battleship.mobile.game.ui.GameScreen
import battleship.mobile.game.ui.GameViewModel
import battleship.mobile.utils.viewModelInit
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val INVALID_GAME_ID = -1
private const val REFRESH_PERIOD_MS = 1000

class GameActivity : ComponentActivity() {

    private val viewModel by viewModels<GameViewModel> {
        val dependencies = (application as DependencyContainer)
        viewModelInit {
            val gameID = intent.getIntExtra(GAME_ID_EXTRA, INVALID_GAME_ID)
            check(gameID != INVALID_GAME_ID)
            GameViewModel(gameID, dependencies.match)
        }
    }

    companion object {
        const val GAME_ID_EXTRA = "GAME_ID"
        fun navigate(origin : Context, gameID : Int) {
            with(origin) {
                startActivity(
                    Intent(this, GameActivity::class.java).also {
                        it.putExtra(GAME_ID_EXTRA, gameID)
                    }
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            val game by viewModel.game.collectAsState()
            GameScreen(
                game = game,
                onBackRequested = { finish() },
                onPlaceRequested = {

                },
                onShootRequested = { target ->
                    viewModel.makeShot(target)
                },
                onForfeitRequested = {
                    viewModel.forfeit()
                }
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Responsible for refreshing!
                // TODO: This polling is hacky and wont work every time, not a good idea is it?
                viewModel.game.collect {
                    when(it) {
                        is GameFight -> if(!it.yourTurn) viewModel.refresh()
                        is GamePlanning -> {
                            Log.v(TAG, "Refreshing at GamePlanning")
                            TODO()
                        }
                        is GameWaiting ->  {
                            Log.v(TAG, "Refreshing at GameWaiting")
                            delay(1000)
                            viewModel.refresh()
                        }
                        null -> {
                            // Question for others, why refresh here and not onCreate()?
                            viewModel.refresh()
                        }
                    }
                }
            }
        }
    }
}

