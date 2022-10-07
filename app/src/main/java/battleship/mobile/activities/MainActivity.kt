package battleship.mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import battleship.mobile.screens.MainScreen
import battleship.mobile.ui.theme.BattleshipmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        onAboutRequest = { navigateToAboutScreen() }

                    )
                    Button(
                        onClick = {
                            val intent = Intent(this, AboutActivity::class.java)
                            startActivity(intent)
                        }
                    ) {
                        Text("info lol")
                    }
                }
            }
        }
    }

    private fun navigateToAboutScreen() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}