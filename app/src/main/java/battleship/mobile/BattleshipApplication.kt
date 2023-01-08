package battleship.mobile

//import androidx.work.* TODO: WE NEED TO ADD THIS
import android.app.Application
import battleship.mobile.game.domain.Board
import battleship.mobile.game.domain.Game
import battleship.mobile.info.adapters.HttpInfo
import battleship.mobile.info.adapters.ServerInfoDtoProperties
import battleship.mobile.info.domain.*
import battleship.mobile.lobby.domain.ActiveGame
import battleship.mobile.lobby.domain.Lobby
import battleship.mobile.setup.domain.Setup
import battleship.mobile.setup.domain.SetupRepository
import battleship.mobile.social.domain.Social
import battleship.mobile.social.domain.User
import battleship.mobile.utils.hypermedia.SubEntity
import battleship.mobile.utils.hypermedia.SubEntityDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.net.URL

const val TAG = "BattleShipApplication"

private val battleshipAPIHome = URL("http://adolfomorgado.com:8080")

val appInfo = AppInfo(
    "BattleShip App v0.1",
    listOf(
        AppAuthor(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
        AppAuthor(46587, "Gonçalo Silva", "a46587@alunos.isel.pt"),
        AppAuthor(48355, "Rodrigo Correia", "a48355@alunos.isel.pt")
    )
)

class BattleshipApplication : DependencyContainer, Application() {

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(Cache(directory = cacheDir, maxSize = 50 * 1024 * 1024))
            .build()
    }

    private val jsonFormatter: Gson by lazy {
        GsonBuilder()
            .registerTypeHierarchyAdapter(
                SubEntity::class.java,
                SubEntityDeserializer<ServerInfoDtoProperties>(ServerInfoDtoProperties::class.java)
            )
            .create()
    }

    override val info: Info by lazy {
        HttpInfo(
            infoUrl = battleshipAPIHome,
            httpClient = httpClient,
            jsonEncoder = jsonFormatter,
        )
    }

    override val game: Game
        get() = FakeGame()

    override val lobby: Lobby
        get() = FakeLobby()

    override val social: Social
        get() = FakeSocial()

    override val setupRepo: SetupRepository
        get() = EmptySetupRepository()

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
}

class FakeLobby : Lobby {

    override suspend fun getActiveGames(): List<ActiveGame> {
        delay(2000)
        return listOf(
            ActiveGame(1, "goncaloaps"),
            ActiveGame(2, "admorgado"),
            ActiveGame(3, "rodrigohcorreia"),
        )
    }

}

class FakeSocial : Social {

    private val aUser = User(
        id = 1,
        username = "john_doe",
        playCount = 2,
        elo = 5
    )

    override suspend fun searchUserByName(name: String): List<User> {
        return emptyList()
    }

    override suspend fun getRanking(page: Int): List<User> {
        delay(3000)
        return buildList { repeat(10) { add(aUser) } }
    }

}

@Suppress("unused")
class FakeInfo : Info {

    override suspend fun getServerInformation(): ServerInfo {
        delay(2000)
        return ServerInfo(
            "0.0.1-FAKE",
            listOf(
                ServerAuthor(1, "adolfo", "adolfomorgado@gmail.com")
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

