package battleship.mobile.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.game.domain.Board
import battleship.mobile.game.domain.Cell
import battleship.mobile.game.domain.Coordinates

const val BOARD_CELL_SIZE = 32
const val BOARD_LINE_SIZE = 1

@Composable
fun TipView(tip : String) {
    Text(
        text = tip,
        style = MaterialTheme.typography.subtitle1
    )
}

private fun Cell.getColor()
    = when(this) {
        Cell.EMPTY -> Color.Cyan
        Cell.MISS -> Color.White
        Cell.SHIP -> Color.Gray
        Cell.HIT -> Color.Gray
        Cell.SUNK -> Color.DarkGray
    }

@Composable
fun CellView(cell : Cell, onClick : (() -> Unit)?) {
    val color = cell.getColor()
    val m = Modifier
        .size(BOARD_CELL_SIZE.dp)
        .background(color)
        .padding(1.dp)
        .let { mod ->
            if(onClick != null)
                mod.clickable(enabled = true, onClick = onClick)
            else
                mod
        }
    Box(m) {

    }
}

@Composable
fun BoardView(board : Board, onCellClick : ((Coordinates) -> Unit)? = null) {
    val rows = board.cells.size
    val columns = board.cells.first().size
    Column {
        repeat(rows) { y ->
            if (y != 0) Spacer(Modifier.height(BOARD_LINE_SIZE.dp))
            Row {
                repeat(columns) { x ->
                    if (x != 0) Spacer(Modifier.width(BOARD_LINE_SIZE.dp))
                    val cell = board.cells[y][x]
                    val onClick = onCellClick?.let { {it(Coordinates(x, y))} }
                    CellView(cell, onClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CellPreview() {
    val cell = Cell.HIT
    CellView(cell, null)
}


@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    val board = Board(List(10) { List(10)  {
        listOf(Cell.EMPTY, Cell.HIT, Cell.SHIP, Cell.MISS).random() // Random colors! :D
    } })
    BoardView(
        board = board,
        onCellClick = { }
    )
}

