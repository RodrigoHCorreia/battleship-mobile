package battleship.mobile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import battleship.mobile.data.Info
import battleship.mobile.ui.TopBar
import battleship.mobile.ui.theme.BattleshipmobileTheme


@Composable
fun AboutScreen(
    appInfo: Info,
    serverInfo: Info? = null,
    onSendEmailRequested: () -> Unit = { },
    onBackRequested: () -> Unit = { }
) {
    BattleshipmobileTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            topBar = { TopBar(onBackRequested = onBackRequested) }
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Battleships",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "version: " + appInfo.version,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Authors: ",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = appInfo.authors.joinToString(separator = "\n") { it.id.toString() + " - " + it.name },
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                TextButton(onClick = onSendEmailRequested) {
                    Icon(Icons.Default.Email, contentDescription = "Localized description")
                    Text("Contact")
                }
            }
        }
    }
}
