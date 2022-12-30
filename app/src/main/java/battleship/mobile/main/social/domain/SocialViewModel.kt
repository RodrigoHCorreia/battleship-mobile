package battleship.mobile.main.social.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SocialViewModel(
) : ViewModel() {

    private val _ranking = MutableStateFlow(0)
    val ranking = _ranking.asStateFlow()

    private val _searchResult = MutableStateFlow(0)
    val searchResult = _searchResult.asStateFlow()

    fun getByName(name : String) {
        viewModelScope.launch {

        }
    }

    fun getRankingPage(page : Int) {
        viewModelScope.launch {

        }
    }


}
