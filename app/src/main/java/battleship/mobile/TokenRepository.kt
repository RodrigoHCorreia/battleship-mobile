package battleship.mobile

import android.content.Context

    class TokenRepository(
    private val context : Context
) {
    private val tokenFile = "BattleshipToken"
    private val tokenKey = "Token"
    private val prefs by lazy {
        context.getSharedPreferences(tokenFile, Context.MODE_PRIVATE)
    }

    var token : String?
        get() = prefs.getString(tokenKey, null)
        set(value) {
            prefs.edit()
                .putString(tokenKey, value)
                .apply()
        }

}
