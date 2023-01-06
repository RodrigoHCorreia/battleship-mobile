package battleship.mobile

import battleship.mobile.game.domain.Game
import battleship.mobile.info.domain.Info
import battleship.mobile.lobby.domain.Lobby
import battleship.mobile.social.domain.Social
import battleship.mobile.setup.domain.SetupRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient

interface DependencyContainer {
    val jsonFormatter : Gson
    val httpClient : OkHttpClient

    val info : Info
    val game : Game
    val lobby : Lobby
    val social : Social
    val setupRepo : SetupRepository
}
