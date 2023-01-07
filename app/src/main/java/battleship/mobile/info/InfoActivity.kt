package battleship.mobile.info

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import battleship.mobile.FakeInfo
import battleship.mobile.R
import battleship.mobile.TAG
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.info.ui.InfoScreen
import battleship.mobile.info.ui.InfoViewModel
import battleship.mobile.info.ui.ServerInfoState
import battleship.mobile.ui.RefreshingState
import battleship.mobile.ui.theme.BattleshipMobileTheme
import battleship.mobile.utils.viewModelInit


class InfoActivity : ComponentActivity() {

    val info = FakeInfo()

    private val viewModel by viewModels<InfoViewModel> {
        viewModelInit {
            InfoViewModel(info)
        }
    }

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (viewModel.info == null)
                viewModel.fetchInfo()

            val loadingState =
                if (viewModel.isLoading) RefreshingState.Refreshing
                else RefreshingState.Idle

            val serverInfo = viewModel.info?.getOrNull() ?: ServerInfo("x.y.z", emptyList())

            BattleshipMobileTheme {
                InfoScreen(
                    appInfo = info.getApplicationInformation(),
                    state = ServerInfoState(serverInfo, loadingState),
                    onSendEmailRequested = { openSendEmail() },
                    onBackRequested = { finish() }
                )
            }
        }
    }

    private fun openSendEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, info
                    .getApplicationInformation()
                    .authors
                    .map { it.email }
                    .toTypedArray())
            }

            startActivity(intent)
        }
        catch (e: ActivityNotFoundException) {
            Log.e(TAG, "Failed to send email", e)
            Toast
                .makeText(
                    this,
                    R.string.activity_info_no_suitable_app,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

}

