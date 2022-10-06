package battleship.mobile

data class Author(
    val id: Int,
    val name : String,
    val email : String
)

data class ServerInfo(
    val authors: List<Author>,
    val version: String,
)

interface ServerInfoService {
    suspend fun getServerInfo () : ServerInfo
}

class FakeServerInfoService : ServerInfoService {
    override suspend fun getServerInfo(): ServerInfo {
        return ServerInfo(
            version = "0.0.1",
            authors = listOf(
                Author(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
                Author(48355, "Rodrigo Correia", "a48355@alunos.isel.pt")
            )
        )
    }
}
