package battleship.mobile.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import battleship.mobile.game.domain.Board
import battleship.mobile.game.domain.Cell

typealias Callback = () -> Unit

const val BOARD_CELL_SIZE = 32

private fun Cell.getColor()
    = when(this) {
        Cell.EMPTY -> Color.Cyan
        Cell.MISS -> Color.White
        Cell.SHIP -> Color.Gray
        Cell.HIT -> Color.Gray
        Cell.SUNK -> Color.DarkGray
    }

@Composable
fun CellView(cell : Cell, onClick : Callback?) {
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
fun BoardView(board : Board, onCellClick : Callback?) {
    val rows = board.cells.size
    val columns = board.cells.first().size
    Column {
        repeat(rows) { y ->
            Row {
                repeat(columns) { x ->
                    val cell = board.cells[y][x]
                    CellView(cell, onCellClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CellPreview() {
    val cell = Cell.EMPTY
    CellView(cell, null)
}


