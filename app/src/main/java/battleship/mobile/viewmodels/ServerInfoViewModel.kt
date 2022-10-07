package battleship.mobile.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.ServerInfoService
import battleship.mobile.TAG
import battleship.mobile.data.Info
import battleship.mobile.utils.loggableMutableStateOf
import kotlinx.coroutines.launch

class ServerInfoViewModel(private val serverInfoService: ServerInfoService) : ViewModel() {

    private val _isLoaded = loggableMutableStateOf(
        at = "ServerInfoViewModel.isLoaded",
        value = false
        )

    val isLoaded : State<Boolean>
        get() = _isLoaded

    private val _serverInfo = loggableMutableStateOf<Info?>(
        at = "ServerInfoViewModel.serverInfo",
        value = null
    )

    val serverInfo : State<Info?>
        get() = _serverInfo

    fun fetchServerInfo() {
        viewModelScope.launch {
            Log.v(TAG, "fetchServerInfo() coroutine starts")
            _isLoaded.value = false
            _serverInfo.value = serverInfoService.getServerInfo()
            _isLoaded.value = true
            Log.v(TAG, "fetchServerInfo() coroutine ends")
        }
    }

}

