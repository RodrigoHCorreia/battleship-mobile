package battleship.mobile.info.impl

import battleship.mobile.info.domain.Info
import battleship.mobile.info.domain.ServerInfo
import com.google.gson.Gson
import okhttp3.OkHttpClient

class HttpInfo(
    private val httpClient: OkHttpClient,
    private val encoder: Gson
) : Info {
    override suspend fun getServerInformation(): ServerInfo {

    }

}


