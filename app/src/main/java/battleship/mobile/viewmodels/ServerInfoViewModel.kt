package battleship.mobile.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ServerInfoViewModel : ViewModel() {

    private val isLoaded = mutableStateOf { false }
    private val serverInfo = mutableStateOf<ServerInfo?> { null }

    val serverInfo
}

