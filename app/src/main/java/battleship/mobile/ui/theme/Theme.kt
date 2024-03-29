package battleship.mobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = black,
    background = blue700,
    surface = darkBlue
)

private val LightColorPalette = lightColors(
    primary = blue700,
    primaryVariant = blue200,
    secondary = black,
    background = blue200,
    surface = blue700
)

@Composable
fun BattleshipMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = colors.surface
    )

    systemUiController.setNavigationBarColor(
        color = colors.background
    )
}