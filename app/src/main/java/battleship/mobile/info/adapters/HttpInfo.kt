package battleship.mobile.info.adapters

import battleship.mobile.appInfo
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
    private val infoUrl: URL,
    private val httpClient: OkHttpClient,
    private val jsonEncoder: Gson
    ) : Info {

    override suspend fun getServerInformation(): ServerInfo {
        val request = buildRequest(url = infoUrl)

        val serverInfoDto = request.send(httpClient) { response ->
            handleResponse<ServerInfoDto>(response, ServerInfoDtoType.type)
        }

        return ServerInfo(serverInfoDto)
    }

    override fun getApplicationInformation(): AppInfo {
        return appInfo
    }

    private fun buildRequest(url: URL): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    private fun <T> handleResponse(response: Response, type: Type): T {
        val contentType = response.body?.contentType()
        return if (response.isSuccessful && contentType != null && contentType == SirenMediaType) {
            try {
                val body = response.body?.string()
                jsonEncoder.fromJson<T>(body, type)
            }
            catch (e: JsonSyntaxException) {
                throw UnexpectedResponseException(response)
            }
        }
        else {
            throw UnexpectedResponseException(response)
        }
    }
}

//TODO: REMOVE THIS FROM THIS FILE AND MOVE IT TO A PROPER ERROR HANDLING FILE
abstract class ApiException(msg: String) : Exception(msg)

abstract class AppException(msg: String) : Exception(msg)

class UnexpectedResponseException(
    private val response: Response? = null
) : ApiException("Unexpected ${response?.code} response from the API.")