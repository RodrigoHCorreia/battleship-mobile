package battleship.mobile.setup.domain

data class ShipType(
    val name : String,
    val size : Int,
    val quantity : Int
)

/**
 * Represents a setup of a game, including it's rules and ships allowed in the game
 */
data class Setup(
    val dimension : Pair<Int, Int>,
    val types : ShipType,
)

