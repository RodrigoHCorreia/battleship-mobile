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

    private var _info by mutableStateOf<Result<ServerInfo>?>(null)
    val info: Result<ServerInfo>?
        get() = _info

    fun fetchInfo() {
        viewModelScope.launch {
            _info =
                try {
                    Result.success(infoService.getServerInformation())
                }
                catch (e: Exception) {
                    //TODO: I will add my AppException Here later
                    Result.failure(e) }
        }
    }

}