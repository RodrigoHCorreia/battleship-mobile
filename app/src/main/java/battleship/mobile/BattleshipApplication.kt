package battleship.mobile

import android.app.Application

class BattleshipApplication: DependenciesContainer, Application() {
    override val serverInfoService: ServerInfoService
        get() = FakeServerInfoService()
}

interface DependenciesContainer {
    val serverInfoService: ServerInfoService
}
