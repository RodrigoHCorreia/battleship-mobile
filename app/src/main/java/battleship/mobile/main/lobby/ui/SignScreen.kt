package battleship.mobile.main.lobby.ui

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.ui.AppButton
import battleship.mobile.ui.theme.BattleshipmobileTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CredentialInputView(
    label : String,
    onChange : (String) -> Unit
) {
    var field by remember { mutableStateOf("") }
    OutlinedTextField(
        value = field,
        onValueChange = onChange,
        label = { Text(label) }
    )
}

@Preview(showBackground = true)
@Composable
fun CredentialInputPreview()
{
    var field by remember { mutableStateOf("") }
    CredentialInputView(label = "Preview", onChange = {
        field = it
    })
}

@Composable
fun SignInScreen(
    onSubmitRequest : (email : String, password : String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column {
        CredentialInputView(label = "Email", onChange = { email = it })
        CredentialInputView(label = "Password", onChange = { password = it })
        AppButton("Sign In") { onSubmitRequest(email, password) }
    }
}

@Composable
fun RegisterScreen(
    onSubmitRequest : (name : String, email : String, password : String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column {
        CredentialInputView(label = "Username", onChange = { username = it })
        CredentialInputView(label = "Email", onChange = { email = it })
        CredentialInputView(label = "Password", onChange = { password = it })
        AppButton(text = "Register") { onSubmitRequest(username, email, password) }
    }
}

@Composable
fun ScreenChooserView(
    onRegisterRequest : () -> Unit,
    onSignInRequest : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = onRegisterRequest) { Text("Register") }
        Spacer(Modifier.width(32.dp))
        Button(onClick = onSignInRequest) { Text("Sign In") }
    }
}

@Preview
@Composable
fun ScreenChooserPreview() {
    BattleshipmobileTheme {
        ScreenChooserView({}, {})
    }
}


enum class SignScreenState {
    REGISTER, SIGN_IN
}

@Composable
fun SignScreen(
    onRegisterSubmit : (name : String, email : String, password : String) -> Unit,
    onSignInSubmit : (email : String, password : String) -> Unit
) {
    //TODO: Screen switch variable here, is it the devil?
    var screen by remember { mutableStateOf(SignScreenState.REGISTER) }

    ScreenChooserView(
        onRegisterRequest = { if(screen != SignScreenState.REGISTER) screen = SignScreenState.REGISTER },
        onSignInRequest = { if(screen != SignScreenState.SIGN_IN) screen = SignScreenState.SIGN_IN }
    )

    when(screen) {
        SignScreenState.REGISTER -> { RegisterScreen(onSubmitRequest = onRegisterSubmit) }
        SignScreenState.SIGN_IN -> { SignInScreen(onSubmitRequest = onSignInSubmit) }
    }
}

