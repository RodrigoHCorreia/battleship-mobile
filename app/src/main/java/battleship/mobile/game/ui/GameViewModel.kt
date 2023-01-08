package battleship.mobile.game.ui

import android.util.Log
import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.R
import battleship.mobile.TAG
import battleship.mobile.game.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    val gameID : Int,
    val match : Match,
    tips : List<String>
) : ViewModel() {

    /**
     * Represents a game, and the board and possible pieces
     * If it's null a game has not been loaded yet
     */
    private val _game = MutableStateFlow<Game?>(null)
    val game : StateFlow<Game?> = _game.asStateFlow()

    val tip = tips.random()

    fun refresh() {
        viewModelScope.launch {
            Log.v(TAG, "Refreshing Game with id ")
            val info = match.getInfo(gameID)
            val newGame = when(info.state) {
                GameState.WAITING -> {
                    GameWaiting(
                        id = info.id,
                        ranked = info.ranked
                    )
                }
                GameState.PLANNING -> {

                    TODO()
                }
                GameState.FIGHTING -> {
                    TODO()
                }
                GameState.FINISHED -> {
                    GameFinished(
                        id = info.id,
                        ranked = info.ranked,
                        board = match.getBoard(gameID),
                        enemyBoard = match.getEnemyBoard(gameID),
                        yourTurn = false,
                        youWin = false, //TODO()

                    )
                }
            }

            _game.emit(newGame)
        } // is join a good idea?
    }


    fun sendBoard() {
        viewModelScope.launch {
            val result = match.placeBoard(gameID, (game.value as GamePlanning).board)
            when(result.status) {
                PlaceResultStatus.INVALID -> {
                    //TODO: make popup!
                }
                PlaceResultStatus.PLACED -> {

                    refresh()
                }
            }
        }
    }

    fun makeShot(target : Coordinates) {
        viewModelScope.launch {
            Log.v(TAG, "Making shot at $target")
            val result = match.makeShot(gameID, target)
            when(result.status) {
                ShotResultStatus.INVALID -> {
                    Log.v(TAG, "Shot Invalid!")
                    //TODO() Make popup!
                }
                ShotResultStatus.MISS -> {
                    Log.v(TAG, "Shot Invalid!")
                }
                ShotResultStatus.HIT -> {
                    Log.v(TAG, "Shot Hit!")
                }
                ShotResultStatus.SUNK -> {
                    Log.v(TAG, "Shot Sunk!")
                }
            }
            if(result.status != ShotResultStatus.INVALID) refresh()
        }
    }

    fun forfeit() {
        viewModelScope.launch {
            Log.v(TAG, "Forfeiting - you dirty quitter!")
            match.forfeit(gameID)
            refresh()
        }
    }
}
