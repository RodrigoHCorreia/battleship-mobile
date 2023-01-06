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
import battleship.mobile.TAG
import battleship.mobile.game.ui.GameScreen
import battleship.mobile.game.ui.GameViewModel
import battleship.mobile.setup.ui.SetupScreen
import battleship.mobile.setup.ui.SetupViewModel
import battleship.mobile.utils.viewModelInit
import kotlinx.coroutines.launch

class GameActivity : ComponentActivity() {

    private val viewModel by viewModels<GameViewModel> {
        viewModelInit {
            SetupViewModel()
        }
    }

    companion object {
        fun navigate(origin : Context) {
            with(origin) {
                startActivity(
                    Intent(this, GameActivity::class.java)
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            SetupScreen(
                onBackRequested = { finish() },
            )
        }
    }
}

