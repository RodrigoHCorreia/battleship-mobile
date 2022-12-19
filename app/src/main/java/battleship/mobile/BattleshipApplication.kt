package battleship.mobile

import android.app.Application
import battleship.mobile.data.Rank
import battleship.mobile.services.RankingService
import kotlinx.coroutines.delay

const val TAG = "BattleShipApplication"

class BattleshipApplication : DependenciesContainer, Application() {
    // TODO: Exchange for the real implementation
    override val serverService: ServerInfoService
        get() = FakeServerInfoService()

    override val rankingService: RankingService
        get() = FakeRankingService()
}

interface DependenciesContainer {
    val serverService: ServerInfoService
    val rankingService: RankingService
}

@Suppress("unused")
private class FakeRankingService : RankingService {
    private val aRank = Rank(
        user = "Ben Dover",
        elo = 80
    )

    override suspend fun fetchUserRank(username: String, mode: RankingService.Mode): Rank {
        delay(3000)
        return aRank
    }

    override suspend fun fetchRanking(mode: RankingService.Mode): List<Rank> {
        delay(3000)
        return buildList { repeat(20) { add(aRank) } }
    }
}
