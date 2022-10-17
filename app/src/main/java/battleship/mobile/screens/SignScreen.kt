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
import battleship.mobile.ui.theme.BattleshipmobileTheme

private val borderSize = 2.dp
private val shapeSize = 20.dp

@Composable
fun SignScreen (onSignUpRequested: (() -> Unit), onSignInRequested: () -> Unit){

    BattleshipmobileTheme {
        Log.i(TAG, "SignScreen Composing")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Button(
                    onClick = { onSignUpRequested() },
                    border = BorderStroke(borderSize, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(shapeSize)
                ) {
                    Text(text = "Sign Up")
                }

                Button(
                    onClick = { onSignInRequested() },
                    border = BorderStroke(borderSize, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(shapeSize)
                ) {
                    Text(text = "Sign In")
                }
            }
        }
    }