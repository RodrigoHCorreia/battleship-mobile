package battleship.mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import battleship.mobile.screens.SignScreen
import battleship.mobile.screens.SignUpScreen
import battleship.mobile.ui.theme.BattleshipmobileTheme

class SignUpActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    SignUpScreen(onSubmit = { navigateToHomeScreen() })
                }
            }
        }

    }
    //TODO: IS THIS NEEDED EVERY TIME WE WANT TO NAVIGATE TO DIFFERENT SCREEN?
    private fun navigateToHomeScreen() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}