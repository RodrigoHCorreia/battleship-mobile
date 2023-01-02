package battleship.mobile

import android.app.Application
import battleship.mobile.game.domain.Board
import battleship.mobile.game.domain.Game
import battleship.mobile.info.domain.*
import battleship.mobile.main.lobby.domain.FakeLobbyService
import battleship.mobile.main.lobby.domain.Lobby
import battleship.mobile.main.social.domain.Social
import battleship.mobile.main.social.domain.User
import battleship.mobile.setup.domain.Setup
import battleship.mobile.setup.domain.SetupRepository
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient

const val TAG = "BattleShipApplication"

const val BASE_URL = "adolfomorgado.com:8080"

val appInfo = AppInfo(
    "BattleShip App v0.1",
    listOf(
        AppAuthor(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
        AppAuthor(46587, "Gonçalo Silva", "a46587@alunos.isel.pt"),
        AppAuthor(48355, "Rodrigo Correia", "a48355@alunos.isel.pt")
    )
)

class BattleshipApplication : DependencyContainer, Application() {

    override val jsonFormatter : Gson
        get() = Gson()

    override val httpClient : OkHttpClient
        get() = OkHttpClient()

    override val info : Info
        get() = FakeInfo()

    override val game: Game
        get() = FakeGame()

    override val lobby : Lobby
        get() = FakeLobbyService()

    override val social: Social
        get() = FakeSocial()

    override val setupRepo: SetupRepository
        get() = EmptySetupRepository()

}

class FakeGame : Game {

    override fun getInfo(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getBoard(): Flow<Board> {
        TODO("Not yet implemented")
    }

    override fun getEnemyBoard(): Flow<Board?> {
        TODO("Not yet implemented")
    }

}

class FakeSocial : Social {

    override suspend fun searchUserByName(name: String): List<User> {
        return emptyList()
    }

    override suspend fun getRanking(page: Int): List<User> {
        return emptyList()
    }

}

class FakeInfo : Info {

    override suspend fun getServerInformation(): ServerInfo {
        delay(2000)
        return ServerInfo(
            "0.0.1-FAKE",
            listOf(
                ServerAuthor(1, "adolfo", "adolfmorg@gmail.com")
            )
        )
    }

    override fun getApplicationInformation(): AppInfo {
        return appInfo
    }

}

class EmptySetupRepository : SetupRepository {

    override var setups: List<Setup>
        get() = emptyList()
        set(value) {}

}
