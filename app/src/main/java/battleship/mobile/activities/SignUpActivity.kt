package battleship.mobile.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import battleship.mobile.screens.SignUpScreen

class SignUpActivity: ComponentActivity() {

    companion object {
        fun navigate(origin: Activity) {
            with(origin) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(
                onBackRequested = { finish() },
                onSubmit = { finish() }
            )
        }

    }
    //TODO: IS THIS NEEDED EVERY TIME WE WANT TO NAVIGATE TO DIFFERENT SCREEN?
    private fun navigateToHomeScreen() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}