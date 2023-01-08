package battleship.mobile

import android.app.Application
import battleship.mobile.game.adapters.FakeMatch
import battleship.mobile.game.domain.Match
import battleship.mobile.info.adapters.HttpInfo
import battleship.mobile.info.adapters.ServerInfoDtoProperties
import battleship.mobile.info.domain.*
import battleship.mobile.main.lobby.domain.ActiveGame
import battleship.mobile.main.lobby.domain.Lobby
import battleship.mobile.main.social.domain.Social
import battleship.mobile.main.social.domain.User
import battleship.mobile.setup.domain.Setup
import battleship.mobile.setup.domain.SetupRepository
import battleship.mobile.utils.hypermedia.SubEntity
import battleship.mobile.utils.hypermedia.SubEntityDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.net.URL

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
            infoUrl = URL(BASE_URL),
            httpClient = httpClient,
            jsonEncoder = jsonFormatter,
        )
//        FakeInfo()
    }
    override val match: Match by lazy {
        FakeMatch()
    }

    override val lobby : Lobby by lazy {
        FakeLobby()
    }

    override val social: Social by lazy {
        FakeSocial()
    }

    override val setupRepo: SetupRepository by lazy {
        EmptySetupRepository()
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

class FakeInfo : Info {

    override suspend fun getServerInformation(): ServerInfo {
        delay(2000)
        return ServerInfo(
            "0.0.1-FAKE",
            listOf(
                ServerAuthor(1, "Adolfo Morgado", "adolfomorgado@gmail.com"),
                ServerAuthor(2, "Rodrigo Correia", "rodrigohcorreia@gmail.com")
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