package battleship.mobile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val DRAWER_ICON_TEXT_SPACE = 16

typealias DrawerCallback = () -> Unit

@Composable
fun DrawerItem(
    icon : ImageVector,
    title : String,
    description : String,
    onClick : DrawerCallback
) {
    val m = Modifier
        .clickable(onClick = onClick)
    Row(modifier = m) {
        Icon(
            imageVector = icon,
            contentDescription = description
        )
        Spacer(Modifier.width(DRAWER_ICON_TEXT_SPACE.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp
        )
    }
}

@Composable
fun DrawerContent(
    onLobbyClick : DrawerCallback,
    onSocialClick : DrawerCallback,
    onInfoClick : DrawerCallback
) {
    Text(
        text = "Battleship Game",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Black
    )
    Divider()
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DrawerItem(
            icon = Icons.Default.PlayArrow,
            title = "Game",
            description = "Get Game Lobby",
            onClick = onLobbyClick
        )
        DrawerItem(
            icon = Icons.Default.Star,
            title = "Social",
            description = "Show Social Screen",
            onClick = onSocialClick
        )
        DrawerItem(
            icon = Icons.Default.Info,
            title = "Info",
            description = "Show application and server Info",
            onClick = onInfoClick
        )
    }
}
