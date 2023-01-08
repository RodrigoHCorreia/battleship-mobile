package battleship.mobile.main.lobby.ui

import androidx.lifecycle.ViewModel
import battleship.mobile.main.lobby.domain.ActiveGame
import battleship.mobile.main.lobby.domain.Lobby
import battleship.mobile.main.lobby.domain.PreferencesTokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LobbyViewModel(
    private val lobby : Lobby,
) : ViewModel() {

    private val _games = MutableStateFlow<List<ActiveGame>>(emptyList())
    val games = _games.asStateFlow()

}