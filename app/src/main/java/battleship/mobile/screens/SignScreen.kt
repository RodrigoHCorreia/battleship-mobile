package battleship.mobile.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import battleship.mobile.TAG
import battleship.mobile.screens.utils.AppDefaultButton
import battleship.mobile.ui.theme.BattleshipmobileTheme

const val SIGN_IN_TEXT = "Sign in"
const val SIGN_UP_TEXT = "Sign up"

@Composable
fun SignScreen (onSignUpRequested: (() -> Unit), onSignInRequested: () -> Unit){

    BattleshipmobileTheme {
        Log.i(TAG, "SignScreen Composing")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                AppDefaultButton(onClick = onSignUpRequested, text = SIGN_UP_TEXT)
                AppDefaultButton(onClick = onSignInRequested, text = SIGN_IN_TEXT)
            }
        }
    }