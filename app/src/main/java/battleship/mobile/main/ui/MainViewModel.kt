package battleship.mobile.main.ui

import androidx.compose.material.rememberScaffoldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _screen = MutableStateFlow(Screen.LOBBY)
    val screen = _screen.asStateFlow()


    fun setScreen(newScreen : Screen) {
        viewModelScope.launch {
            _screen.emit(newScreen)
        }
    }

}


