package battleship.mobile.info.domain

data class AppAuthor(
    val id: Int,
    val name : String,
    val email : String
)

data class AppInfo(
    val version : String,
    val authors : List<AppAuthor>
)


