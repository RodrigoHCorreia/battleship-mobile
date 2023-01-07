package battleship.mobile.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import battleship.mobile.lobby.LobbyActivity
import battleship.mobile.login.ui.LoginScreen

class LoginActivity : ComponentActivity() {

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onLoginRequested = { LobbyActivity.navigate(origin = this) },
                onRegisterRequested = { LobbyActivity.navigate(origin = this) }
            )
        }
    }

}