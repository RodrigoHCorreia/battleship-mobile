package battleship.mobile.main.lobby.domain

data class ActiveGame(
    val id : Int,
    val player : String
)

interface Lobby {
    suspend fun getActiveGames() : List<ActiveGame>
}
