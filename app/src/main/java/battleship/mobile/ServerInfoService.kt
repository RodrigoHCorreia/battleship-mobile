package battleship.mobile

import battleship.mobile.data.Info
import kotlinx.coroutines.delay

data class Author(
    val id: Int,
    val name: String,
    val email: String
)


interface ServerInfoService {
    suspend fun getServerInfo(): Info
}

class FakeServerInfoService : ServerInfoService {
    override suspend fun getServerInfo(): Info {
        val info = Info(
            version = "0.0.1",
            authors = listOf(
                Author(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
                Author(48355, "Rodrigo Correia", "a48355@alunos.isel.pt")
            )
        )
        delay(1000)
        return info
    }
}
