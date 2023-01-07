package battleship.mobile.game.domain

enum class GameState { WAITING, PLANNING, FIGHTING, FINISHED }
data class GameInfo(
    val id : Int,
    val state : GameState,
    val ranked : Boolean,
)

/**
 *
 */
sealed class Game(
    val id : Int,
    val ranked : Boolean,
)

class GameWaiting(
    id : Int,
    ranked : Boolean,
) : Game(id, ranked)

class GamePlanning(
    id : Int,
    ranked : Boolean,
    val board : BoardProposal
) : Game(id, ranked)

class GameFight(
    id : Int,
    ranked : Boolean,
    val board : Board,
    val enemyBoard : Board,
    val yourTurn : Boolean,
) : Game(id, ranked)

class GameFinished(
    id : Int,
    ranked : Boolean,
    val board : Board,
    val enemyBoard : Board,
    val yourTurn : Boolean,
    val youWin : Boolean
) : Game(id, ranked)
