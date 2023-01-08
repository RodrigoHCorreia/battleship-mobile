package battleship.mobile.info.domain

import battleship.mobile.appInfo
import battleship.mobile.info.adapters.ServerInfo
import battleship.mobile.info.adapters.ServerInfoDto
import battleship.mobile.info.adapters.ServerInfoDtoType
import battleship.mobile.utils.hypermedia.SirenMediaType
import battleship.mobile.utils.send
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.reflect.Type
import java.net.URL

interface Info {

    suspend fun getServerInformation() : ServerInfo

    fun getApplicationInformation(): AppInfo

}
