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
import battleship.mobile.DependencyContainer
import battleship.mobile.R
import battleship.mobile.TAG
import battleship.mobile.info.ui.InfoScreen
import battleship.mobile.info.ui.InfoViewModel
import battleship.mobile.ui.theme.BattleshipMobileTheme
import battleship.mobile.utils.viewModelInit


class InfoActivity : ComponentActivity() {

    private val info by lazy { (application as DependencyContainer).info }

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

        Log.v(TAG, "InfoActivity.onCreate()")
        if (viewModel.info == null) {
            viewModel.fetchInfo()
        }
        setContent {
            val serverInfo = viewModel.info?.getOrNull()

            BattleshipMobileTheme {
                InfoScreen(
                    serverInfo = serverInfo,
                    appInfo = info.getApplicationInformation(),
                    onSendEmailRequested = { openSendEmail() },
                    onBackRequested = { finish() }
                )
            }
        }
    }

    private fun openSendEmail() {
        try {
            val intent =
                Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, info
                        .getApplicationInformation()
                        .authors
                        .map { it.email }
                        .toTypedArray())
                }

            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "Failed to send email", e)
            Toast
                .makeText(
                    this,
                    R.string.info_no_suitable_app,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

}



