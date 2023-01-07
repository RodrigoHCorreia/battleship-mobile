package battleship.mobile.setup.adapters

import android.content.Context
import battleship.mobile.setup.domain.Setup
import battleship.mobile.setup.domain.SetupRepository
import battleship.mobile.utils.hypermedia.SirenEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SetupRepositoryImpl(
    private val context : Context,
    private val encoder : Gson
) : SetupRepository {

    private val defaultSetup = " "
    private val setupListType : TypeToken<List<Setup>> =
        object : TypeToken<List<Setup>>() { /*NOTHING */ }
    private val setupKey = "Setup"

    private val prefs by lazy {
        context.getSharedPreferences("BattleshipSetup", Context.MODE_PRIVATE)
    }

    override var setups: List<Setup>
        get() {
            val savedSetups = prefs.getString(setupKey, defaultSetup)
            return encoder.fromJson(savedSetups, setupListType.type) // TODO this really needs testing!
        }
        set(value) {
            val encondedValue = encoder.toJson(value)
            prefs.edit()
                .putString(setupKey, encondedValue)
                .apply()
        }

}