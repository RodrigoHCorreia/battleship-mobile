package battleship.mobile.game.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.game.domain.Coordinates
import battleship.mobile.game.domain.Game
import battleship.mobile.utils.loggableMutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    game : Game
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameScreenState())
    val uiState : StateFlow<GameScreenState> = _uiState


    fun makeShot(target : Coordinates) {
        viewModelScope.launch {

        }
    }

}
