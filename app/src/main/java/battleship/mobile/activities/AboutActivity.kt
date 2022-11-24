package battleship.mobile.activities

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import battleship.mobile.DependenciesContainer
import battleship.mobile.R
import battleship.mobile.ServerInfoService
import battleship.mobile.TAG
import battleship.mobile.data.Info
import battleship.mobile.data.appInfo
import battleship.mobile.screens.AboutScreen
import battleship.mobile.ui.theme.BattleshipmobileTheme
import battleship.mobile.viewmodels.ServerInfoViewModel

class AboutActivity : ComponentActivity() {

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private val serverInfoService: ServerInfoService by lazy {
        (application as DependenciesContainer).serverService
    }

    @Suppress("UNCHECKED_CAST")
    private val vm by viewModels<ServerInfoViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ServerInfoViewModel(serverInfoService) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (!vm.isLoaded.value) vm.fetchServerInfo()
            val serverInfo: Info? = vm.serverInfo.value

            BattleshipmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AboutScreen(
                        appInfo = appInfo,
                        serverInfo = serverInfo,
                        onSendEmailRequested = { openSendEmail() },
                        onBackRequested = { finish() }
                    )
                }
            }
        }
    }

    private fun openSendEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, appInfo.authors.map { it.email }.toTypedArray())
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

