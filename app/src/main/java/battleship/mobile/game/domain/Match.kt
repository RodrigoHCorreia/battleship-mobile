package battleship.mobile.game.domain

interface Match {
    // Getters
    suspend fun getInfo(gameID: Int): GameInfo

    /**
     * @throws IllegalStateException If Game has not passed the planning phase yet
     */
    suspend fun getBoard(gameID: Int): Board

    /**
     * @throws IllegalStateException If Game has not passed the planning phase yet
     */
    suspend fun getEnemyBoard(gameID: Int): Board

    /**
     * @throws IllegalStateException If Game has not passed the waiting phase yet
     */
    suspend fun getOpponent(gameID : Int): Int?

    // Actions
    /**
     * @throws IllegalStateException If Game is not in Planning Phase
     */
    suspend fun placeBoard(gameID: Int, board: BoardProposal): PlaceResult

    /**
     * @throws IllegalStateException If game is not in fighting phase
     */
    suspend fun makeShot(gameID: Int, target: Coordinates) : ShotResult

    /**
     * @throws IllegalStateException if game is not in fighting phase
     */
    suspend fun forfeit(gameID: Int) // TODO Not Actually implemented!
}

