package battleship.mobile

import android.app.Application

const val TAG = "BattleShipApplication"

class BattleshipApplication : DependenciesContainer, Application() {
    override val serverService: ServerInfoService
        // TODO: Exchange for the real implementation
        get() = FakeServerInfoService()
}

interface DependenciesContainer {
    val serverService: ServerInfoService
}