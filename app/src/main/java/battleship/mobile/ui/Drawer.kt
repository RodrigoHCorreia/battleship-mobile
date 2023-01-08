package battleship.mobile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val DRAWER_ICON_TEXT_SPACE = 16

const val GameButtonTag = "Game"
const val SocialButtonTag = "Social"
const val InfoButtonTag = "Info"
const val LogoutButtonTag = "Logout"

data class DrawerItem(
    val icon : ImageVector,
    val title : String,
    val description : String,
    val onClick : () -> Unit,
    val tag : String = ""
)

@Composable
fun DrawerItemView(
    item : DrawerItem
) {
    val m = Modifier
        .clickable(onClick = item.onClick)
        .testTag(item.tag)
    Row(modifier = m) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
            tint = MaterialTheme.colors.primary
        )
        Spacer(Modifier.width(DRAWER_ICON_TEXT_SPACE.dp))
        Text(
            text = item.title,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun DrawerHeaderView() {
    Text(
        text = "Battleship",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Black,
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun DrawerContent(
    drawerItems : List<DrawerItem>
) {
    Column(
    ) {
        DrawerHeaderView()
        Divider()
        Column {
            drawerItems.forEach { item ->
                DrawerItemView(item = item)
            }
        }
    }
}
