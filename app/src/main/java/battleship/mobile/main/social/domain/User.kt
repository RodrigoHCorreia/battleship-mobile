package battleship.mobile.main.social.domain

/**
 * Used to represent a user in the battleship application
 */
data class User(
    val id : Int,
    val username : String,
    val playCount : Int,
    val elo : Int
)





