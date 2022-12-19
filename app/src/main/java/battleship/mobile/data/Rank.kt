package battleship.mobile.data

/**
 * The domain entity for representing ranks
 */
data class Rank(val user: String, val elo: Int) {
    init { require(user.isNotBlank()) }
}