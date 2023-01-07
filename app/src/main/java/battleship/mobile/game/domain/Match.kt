package battleship.mobile.game.domain

interface Match {
    // Getters
    suspend fun getInfo(gameID: Int): GameInfo

    /**
     * @throws IllegalStateException If Game has not passed the planning phase yet
     */
    suspend fun getBoard(gameId: Int): Board

    /**
     * @throws IllegalStateException If Game has not passed the planning phase yet
     */
    suspend fun getEnemyBoard(gameId: Int): Board

    /**
     * @throws IllegalStateException If Game has not passed the waiting phase yet
     */
    suspend fun getOpponent(gameId : Int): Int?

    // Actions
    /**
     * @throws IllegalStateException If Game is not in Planning Phase
     */
    suspend fun placeBoard(gameId: Int, board: BoardProposal): PlaceResult

    /**
     * @throws IllegalStateException If game is not in fighting phase
     */
    suspend fun makeShot(gameID: Int, target: Coordinates) : ShotResult

    /**
     * @throws IllegalStateException if game is not in fighting phase
     */
    suspend fun forfeit(gameID: Int) // TODO Not Actually implemented!
}

