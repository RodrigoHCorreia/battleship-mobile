package battleship.mobile.info.adapters

import battleship.mobile.info.domain.AppInfo
import battleship.mobile.info.domain.Info
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.utils.hypermedia.SirenMediaType
import battleship.mobile.utils.send
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.reflect.Type
import java.net.URL

class HttpInfo(
    private val url : URL,
    private val httpClient: OkHttpClient,
    private val jsonEncoder: Gson,
    private val appInfo : AppInfo
) : Info {
    override suspend fun getServerInformation(): ServerInfo { //TODO RECIEVE SERVER INFO

        val request = buildRequest(url)

        val infoDto = request.send(httpClient) {
            handleResponse<InfoDto>(it, InfoDtoType.type)
        }
        val prop = infoDto.properties
        checkNotNull(prop) // TODO: switch this out to throw a custom exception

        return prop.toServerInfo()
    }

    override fun getApplicationInformation(): AppInfo {
        return appInfo
    }

    private fun <T> handleResponse(response: Response, type: Type): T {
        val contentType = response.body?.contentType()
        return if (response.isSuccessful && contentType != null && contentType == SirenMediaType) {
            try {
                val body = response.body?.string()
                jsonEncoder.fromJson<T>(body, type)
            }
            catch (e: JsonSyntaxException) {
                TODO()
                //throw UnexpectedResponseException(response)
            }
        }
        else {
            TODO()
            //throw UnexpectedResponseException(response = response)
        }
    }

    private fun buildRequest(url : URL) =
        with(Request.Builder()) {
            this
        }.url(url).build()


}


