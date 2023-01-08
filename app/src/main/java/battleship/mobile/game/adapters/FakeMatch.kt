package battleship.mobile.game.adapters

import battleship.mobile.game.domain.*

class FakeMatch : Match {
    override suspend fun getInfo(gameID: Int): GameInfo {
        return GameInfo(
            id = 521,
            state = GameState.WAITING,
            ranked = true
        )
    }

    override suspend fun getBoard(gameID: Int): Board {
        return Board(List(10) { List(10)  { Cell.EMPTY } })
    }

    override suspend fun getEnemyBoard(gameID: Int): Board {
        return Board(List(10) { List(10)  { Cell.EMPTY } })
    }

    override suspend fun getOpponent(gameID: Int): Int? {
        return 2346
    }

    override suspend fun placeBoard(gameID: Int, board: BoardProposal): PlaceResult {
        TODO()
    }

    override suspend fun makeShot(gameID: Int, target: Coordinates): ShotResult {
        TODO("Not yet implemented")
    }

    override suspend fun forfeit(gameID: Int) {
        TODO("Not yet implemented")
    }

}
