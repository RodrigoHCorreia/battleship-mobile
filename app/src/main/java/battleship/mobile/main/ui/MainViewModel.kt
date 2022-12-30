package battleship.mobile.main.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    val screen = mutableStateOf(MainScreenState.LOBBY)

}

