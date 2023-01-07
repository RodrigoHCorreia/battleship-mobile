package battleship.mobile.game.domain

enum class PlaceResultStatus {
    INVALID, PLACED
}

data class PlaceResult(
    val status : PlaceResultStatus
)

enum class ShotResultStatus {
    INVALID, MISS, HIT, SUNK
}

data class ShotResult(
    val status : ShotResultStatus,
    val sunk : String?
)