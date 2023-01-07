package battleship.mobile.lobby.domain

interface Lobby {
    suspend fun getActiveGames() : List<ActiveGame>
}
