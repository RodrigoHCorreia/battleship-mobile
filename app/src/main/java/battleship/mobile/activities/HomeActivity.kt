package battleship.mobile.activities

import RankingActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import battleship.mobile.screens.HomeScreen
import battleship.mobile.ui.theme.BattleshipmobileTheme

class HomeActivity : ComponentActivity() {

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(
                        // Navegacao no logout deve ser revista
                        onMMRequest = { finish() },
                        onRankingRequested = { RankingActivity.navigate(origin = this) },
                        onLogoutRequested = { SignActivity.navigate(origin = this) },
                        onAboutRequest = { AboutActivity.navigate(origin = this) }
                    )
                }
            }
        }
    }

    private fun navigateToAboutScreen() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

}