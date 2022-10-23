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
import battleship.mobile.ui.theme.BattleshipmobileTheme

class SignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ){
                    SignScreen(
                        onSignUpRequested = { SignUpActivity.navigate(origin = this) },
                        onSignInRequested = { SignInActivity.navigate(origin = this) },
                    )
                }
            }
        }
    }

    //TODO: IS THIS NEEDED EVERY TIME WE WANT TO NAVIGATE TO DIFFERENT SCREEN?
    private fun navigateToSignUpScreen() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSignInScreen() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}

