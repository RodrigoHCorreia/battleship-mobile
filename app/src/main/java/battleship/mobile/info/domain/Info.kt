package battleship.mobile.info.domain

import battleship.mobile.appInfo

interface Info {

    suspend fun getServerInformation() : ServerInfo

    fun getApplicationInformation(): AppInfo

}

class RealInfo : Info {

    override suspend fun getServerInformation(): ServerInfo {
        TODO("Not yet implemented")
    }

    override fun getApplicationInformation(): AppInfo {
        return appInfo
    }

}

