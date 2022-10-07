package battleship.mobile.activities

import AboutScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import battleship.mobile.DependenciesContainer
import battleship.mobile.ServerInfoService
import battleship.mobile.data.Info
import battleship.mobile.data.appInfo
import battleship.mobile.viewmodels.ServerInfoViewModel

class AboutActivity : ComponentActivity() {

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

            AboutScreen(appInfo, serverInfo)
        }
    }
}

