package battleship.mobile.game.domain

data class Coordinates(
    val x : Int,
    val y : Int
) {
    init {
        require(x in 0..9)
        require(y in 0..9)
    }
}

