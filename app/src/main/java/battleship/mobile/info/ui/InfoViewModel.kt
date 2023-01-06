package battleship.mobile.info.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.info.domain.Info
import battleship.mobile.info.domain.ServerInfo
import kotlinx.coroutines.launch


class InfoViewModel (
    private val infoService: Info
) : ViewModel() {

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading

    private var _info by mutableStateOf<Result<ServerInfo>?>(null)
    val info: Result<ServerInfo>?
        get() = _info

    fun fetchInfo() {
        viewModelScope.launch {
            _isLoading = true
            _info =
                try {
                    Result.success(infoService.getServerInformation())
                }
                catch (e: Exception) { Result.failure(e) }
            _isLoading = false
        }
    }

}