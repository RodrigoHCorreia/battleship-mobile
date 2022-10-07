import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val colors = listOf(
    Color.Red,
    Color.Yellow,
    Color.Black,
    Color.Green,
    Color.Magenta,
    Color.Cyan,
    Color.Blue
)

@Composable
fun CellView(x: Int, y: Int) {
    val modifier = Modifier
        .size(32.dp)
        .background(colors.random())
    //.border(1.dp, Color.Black)
    Box(
        modifier = modifier
    ) {

    }
}


@Composable
fun BoardView() {

    Column {
        for (y in 1..10) {
            Row {
                for (x in 1..10) {
                    CellView(x, y);
                }
            }
        }
    }
}

@Composable
fun BoardWithGuardView() {

    BoardView()
}

@Preview(showBackground = true)
@Composable
fun emptyBoardPreview() {
    BoardView();
}
