package battleship.mobile.lobby.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.lobby.domain.ActiveGame
import battleship.mobile.lobby.domain.Lobby
import kotlinx.coroutines.launch

class LobbyViewModel(
    private val lobbyService: Lobby
) : ViewModel() {

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading

    private var _activeGames by mutableStateOf<Result<List<ActiveGame>>?>(null)
    val activeGames : Result<List<ActiveGame>>?
        get() = _activeGames

    val token = mutableStateOf<String?>(null)

    fun signIn() {

    }

    fun register() {

    }

    fun getActiveGames() {
        viewModelScope.launch {
            _isLoading = true
            _activeGames =
                try {
                    Result.success(lobbyService.getActiveGames())
                }
                catch (e : Exception) { Result.failure(e) }
            _isLoading = false
        }
    }

}
