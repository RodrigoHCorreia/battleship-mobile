package battleship.mobile.info.domain

import kotlinx.coroutines.delay

interface Info {

    suspend fun getServerInformation() : ServerInfo

}

class FakeInfo : Info {
    override suspend fun getServerInformation(): ServerInfo {
        delay(1000)
        return ServerInfo(
            "0.0.1-FAKE",
            listOf(
                ServerAuthor(1, "adolfo", "adolfmorg@gmail.com")
            )
        )
    }

}

