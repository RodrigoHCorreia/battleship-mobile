package battleship.mobile.info.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import battleship.mobile.R
import battleship.mobile.info.domain.AppAuthor
import battleship.mobile.info.domain.AppInfo
import battleship.mobile.info.domain.ServerAuthor
import battleship.mobile.info.domain.ServerInfo

private const val SPACE = " "
private const val SEP = " - "

@Composable
fun SendEmailButton(onSendEmailRequested: () -> Unit) {
    TextButton(onClick = onSendEmailRequested) {
        Icon(Icons.Default.Email, contentDescription = "Localized description")
        Text(
            text = stringResource(id = R.string.info_send_email),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ServerInfoView(serverInfo: ServerInfo? = null) {
    Text(
        text = stringResource(id = R.string.info_server_info_display),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold
    )
    if (serverInfo == null) {
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 5.dp
        )
    } else {
        Text(
            text = stringResource(id = R.string.info_version_display) + SPACE + serverInfo.version,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary
        )
        AuthorServerView(serverAuthors = serverInfo.serverAuthors)
    }
}

@Composable
fun AppInfoView(appInfo: AppInfo) {
    Text(
        text = stringResource(id = R.string.app_title),
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Black
    )
    Text(
        text = stringResource(id = R.string.info_app_info_display),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Black
    )
    Text(
        text = stringResource(id = R.string.info_version_display) + SPACE + appInfo.version,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary
    )
    AuthorAppView(appAuthors = appInfo.authors)
}

@Composable
fun AuthorAppView(appAuthors: List<AppAuthor>) {
    Text(
        text = stringResource(id = R.string.info_authors_display),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Black
    )
    Text(
        text = appAuthors.joinToString(separator = "\n") { it.id.toString() + SEP + it.name },
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun AuthorServerView(serverAuthors: List<ServerAuthor>) {
    Text(
        text = stringResource(id = R.string.info_authors_display),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Black
    )
    Text(
        text = serverAuthors.joinToString(separator = "\n") { it.id.toString() + SEP + it.name },
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary
    )
}