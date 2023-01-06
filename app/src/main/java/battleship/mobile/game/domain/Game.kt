package battleship.mobile.game.domain

import kotlinx.coroutines.flow.Flow

/**
 * Represents a game between two players and the possible actions a player can make
 */
interface Game
{
    /**
     *
     */
    fun getInfo() : Flow<String>
    fun getBoard() : Flow<Board>
    fun getEnemyBoard() : Flow<Board?>
}


