package battleship.mobile.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import battleship.mobile.ui.theme.BattleshipMobileTheme

@Composable
fun AppButton(
    text : String,
    onClick : () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 12.dp
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colors.background,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    BattleshipMobileTheme() {
        AppButton("Preview") { }
    }
}
