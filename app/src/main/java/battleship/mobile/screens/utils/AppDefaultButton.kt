package battleship.mobile.screens.utils
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val borderSize = 2.dp
private val shapeSize = 50.dp

//TODO make UI more beautiful
@Composable
fun AppDefaultButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = { onClick() },
        border = BorderStroke(borderSize, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(shapeSize),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 12.dp
        )
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun AppDefaultButtonPreview() {
    AppDefaultButton(onClick = {}, text = "Sign in")
}