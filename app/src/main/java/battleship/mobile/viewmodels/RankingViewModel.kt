package battleship.mobile.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import battleship.mobile.data.Rank
import battleship.mobile.services.RankingService
import kotlinx.coroutines.launch

class RankingViewModel (
    private val rankingService: RankingService
) : ViewModel() {

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading

    private var _ranking by mutableStateOf<Result<List<Rank>>?>(null)
    val ranking: Result<List<Rank>>?
        get() = _ranking

    fun fetchRanking(forcedRefresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading = true
            _ranking =
                try {
                    Result.success(rankingService.fetchRanking(
                        if (forcedRefresh) RankingService.Mode.FORCE_REMOTE
                        else RankingService.Mode.AUTO
                    ))
                }
                catch (e: Exception) { Result.failure(e) }
            _isLoading = false
        }
    }

}


