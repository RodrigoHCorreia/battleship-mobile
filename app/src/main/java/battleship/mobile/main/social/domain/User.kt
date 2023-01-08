package battleship.mobile.main.social.domain

data class User(
    val id : Int,
    val playCount : Int,
    val username : String,
    val elo : Int,
)

