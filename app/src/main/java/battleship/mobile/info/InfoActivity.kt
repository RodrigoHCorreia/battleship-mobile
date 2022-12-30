package battleship.mobile.info

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import battleship.mobile.BattleshipApplication
import battleship.mobile.info.ui.InfoScreen
import battleship.mobile.info.ui.InfoViewModel
import battleship.mobile.ui.NavigationHandlers
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme
import battleship.mobile.utils.viewModelInit


class InfoActivity : ComponentActivity() {

    private val viewModels by viewModels<InfoViewModel> {
        viewModelInit {
            InfoViewModel()
        }
    }

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)



        setContent {
            BattleshipmobileTheme {
                InfoScreen(
                    onBackRequested = { finish() }
                )
            }
        }
    }
}

