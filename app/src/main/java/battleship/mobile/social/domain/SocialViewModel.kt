package battleship.mobile.social.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SocialViewModel(
    private val socialService: Social
) : ViewModel() {

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading

    private var _ranking by mutableStateOf<Result<List<User>>?>(null)
    val ranking: Result<List<User>>?
        get() = _ranking

    private val _searchResult = MutableStateFlow(0)
    val searchResult = _searchResult.asStateFlow()

    fun getByName(name : String) {
        viewModelScope.launch {

        }
    }

    fun getRanking(page : Int) {
        viewModelScope.launch {
            _isLoading = true
            _ranking =
                try {
                    Result.success(socialService.getRanking(page))
                }
                catch (e: Exception) { Result.failure(e) }
            _isLoading = false
        }
    }

}