package battleship.mobile.game.domain

enum class Cell {
    EMPTY, MISS, SHIP, HIT, SUNK
}

data class Board (
    val cells : List<List<Cell>>
)
