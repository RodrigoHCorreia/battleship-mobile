package battleship.mobile.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import battleship.mobile.DependenciesContainer
import battleship.mobile.TAG
import battleship.mobile.screens.RankingScreen
import battleship.mobile.screens.RankingScreenState
import battleship.mobile.ui.RefreshingState
import battleship.mobile.viewmodels.RankingViewModel
import battleship.mobile.utils.viewModelInit

class RankingActivity : ComponentActivity() {

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, RankingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private val dependencies by lazy { application as DependenciesContainer }

    private val viewModel: RankingViewModel by viewModels {
        viewModelInit {
            RankingViewModel(dependencies.rankingService)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "RankingActivity.onCreate()")
        setContent {
            if (viewModel.ranking == null)
                viewModel.fetchRanking()

            val loadingState =
                if (viewModel.isLoading) RefreshingState.Refreshing
                else RefreshingState.Idle

            val ranking = viewModel.ranking?.getOrNull() ?: emptyList()
            RankingScreen(
                state = RankingScreenState(ranking, loadingState),
                onUpdateRequest = { viewModel.fetchRanking(forcedRefresh = true) },
                onBackRequested = { finish() }
            )
        }
    }

    // TODO: Error messages

}


