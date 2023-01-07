package battleship.mobile.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.theme.BattleshipMobileTheme

typealias Callback = () -> Unit

data class NavigationHandlers(
    val onDrawerRequested: Callback? = null,
    val onBackRequested: Callback? = null
) {
    init {
        // Only Back Button or Drawer Button
        val lhs = onDrawerRequested != null
        val rhs = onBackRequested != null
        check(lhs xor rhs)
    }
}

// Test tags for the TopBar navigation elements
const val OpenDrawerTag = "OpenDrawer"
const val NavigateBackTag = "NavigateBack"

@Composable
fun TopBar(
    navigation : NavigationHandlers
) {
    val modifier = Modifier
    TopAppBar(
        title = {},
        modifier = modifier,
        navigationIcon = {
            navigation.onDrawerRequested?.let {
                IconButton(
                    onClick = it,
                    modifier = Modifier.testTag(OpenDrawerTag)
                ) {
                    Icon(Icons.Default.List, "Open Drawer")
                }
            }
            navigation.onBackRequested?.let {
                IconButton(
                    onClick = it,
                    modifier = Modifier.testTag(NavigateBackTag)
                ) {
                    Icon(Icons.Default.ArrowBack, "Go back")
                }
            }
        }
    )
    /*
    TopAppBar(
        //TODO I dont like title
        // title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (navigation.onBackRequested != null) {
                IconButton(onClick = navigation.onBackRequested) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            } else {

            }
        }
    )

     */
}

@Preview
@Composable
private fun TopBarPreviewBack() {
    BattleshipMobileTheme {
        TopBar(NavigationHandlers({}))
    }
}
@Preview
@Composable
private fun TopBarPreviewDrawer() {
    BattleshipMobileTheme {
        TopBar(NavigationHandlers(null, {}))
    }
}
