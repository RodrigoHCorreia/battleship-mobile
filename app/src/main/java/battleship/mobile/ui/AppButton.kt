package battleship.mobile.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.ui.theme.BattleshipmobileTheme

private const val BORDER_SIZE = 2
private const val SHAPE_SIZE = 50


@Composable
fun AppButton(
    text : String,
    onClick : () -> Unit
) {
    Button(
        onClick = onClick,
        border = BorderStroke(BORDER_SIZE.dp, MaterialTheme.colors.secondary),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 12.dp
        )
    ) {
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    BattleshipmobileTheme() {
        AppButton("Preview") { }
    }
}



