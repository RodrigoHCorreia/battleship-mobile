package battleship.mobile.lobby.domain


interface Lobby {


    suspend fun getActiveGames() : List<Int>


}

class FakeLobbyService : Lobby {
    override suspend fun getActiveGames(): List<Int> {
        TODO("Not yet implemented")
    }


}
