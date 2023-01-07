package battleship.mobile.game.adapters

import android.content.Context
import battleship.mobile.game.domain.*
import battleship.mobile.utils.*
import battleship.mobile.utils.hypermedia.SirenMediaType
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.reflect.Type
import java.net.URL

class HttpMatch(
    val encoder : Gson,
    val client : OkHttpClient,
    val context : Context
) : Match {

    override suspend fun getInfo(gameID: Int): GameInfo {

        val url = URL("")
        val token = ""
        val request = buildGetRequest(url, token)
        TODO()
    }


    override suspend fun getBoard(gameId: Int): Board {
        val url = URL("")
        val token = ""

        /*
        val request = buildGetRequest(url, token)
        val board : Board = request.send(client) { response ->
            when(response.code) {
                200 -> {
                    val body = response.body ?: throw UnexpectedError
                }

                else -> throw UnknownError("")
            }
        }

         */
        TODO()
        //return board;
    }

    override suspend fun getEnemyBoard(gameId: Int): Board {
        val url = URL("")
        val token = ""

        TODO()
        /*
        val request = buildRequest(url, token)
        val board = request.send(client) { response ->

            null
        }
        return board

         */
    }

    override suspend fun getOpponent(gameId: Int): Int? {
        TODO()
    }

    override suspend fun placeBoard(gameId: Int, board: BoardProposal): PlaceResult {
        val url = URL("")
        val token = ""
        TODO()
        /*
        val request = buildRequest(url, token)
        request.send(client) {

        }

         */
    }

    override suspend fun makeShot(gameID: Int, target: Coordinates): ShotResult {
        val url = URL("")
        val token = ""

        TODO()
        /*
        val request = buildRequest(url, token)
        request.send(client) { response ->

        }

         */
    }

    override suspend fun forfeit(gameID: Int) {
        val url = URL("")
        val token = ""
        val request = buildGetRequest(url, token)
        request.send(client) {

        }
    }

    private fun <T> handleResponse(response : Response, type : Type) : T {
        val contentType = response.body?.contentType()
        when(response.code) {
            HttpCode.UNAUTHORIZED   -> throw UnauthorizedException("Requires a valid token!")
            HttpCode.FORBIDDEN      -> throw ForbiddenException("You are not participating in this game!")
            HttpCode.NOT_FOUND      -> throw NotFoundException("The game you tried to reach does not exist")
        }
        return if (response.isSuccessful && contentType == SirenMediaType) {
            try {
                val body = response.body?.string()
                encoder.fromJson<T>(body, type)
            }
            catch (e: JsonSyntaxException) {
                throw UnexpectedException(response.message)
            }
        } else {
            throw UnexpectedException("An unexpected error has occurred!")
        }
    }

    private fun buildGetRequest(url : URL, token : String) =
        Request.Builder()
            .url(url)
            .build()

    private fun buildPostRequest(url : URL, token : String) =
        Request.Builder()
            .url(url)
            .build()
}
