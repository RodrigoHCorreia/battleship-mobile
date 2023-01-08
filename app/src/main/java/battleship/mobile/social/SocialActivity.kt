package battleship.mobile.social

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import battleship.mobile.DependencyContainer
import battleship.mobile.FakeSocial
import battleship.mobile.social.domain.SocialViewModel
import battleship.mobile.social.ui.SocialScreen
import battleship.mobile.social.ui.SocialScreenState
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.theme.BattleshipMobileTheme
import battleship.mobile.utils.viewModelInit

class SocialActivity : ComponentActivity() {

    val social by lazy { (application as DependencyContainer).social }

    private val viewModel by viewModels<SocialViewModel> {
        viewModelInit {
            SocialViewModel(social)
        }
    }

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, SocialActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (viewModel.ranking == null)
                viewModel.getRanking(1)

            val loadingState =
                if (viewModel.isLoading) RefreshingState.Refreshing
                else RefreshingState.Idle

            val ranking = viewModel.ranking?.getOrNull() ?: emptyList()

            BattleshipMobileTheme {
                SocialScreen(
                    state = SocialScreenState(ranking, loadingState),
                    onUpdateRequest = { viewModel.getRanking(1) },
                    onBackRequested = { finish() }
                )
            }
        }
    }

}