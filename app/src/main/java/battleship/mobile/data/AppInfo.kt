package battleship.mobile.data

import battleship.mobile.Author

data class AppInfo(
    val version : String,
    val authors : List<Author>
)

val appInfo = AppInfo(version = "0.1 ALPHA", authors = listOf(
    Author(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
    Author(48355, "Rodrigo Correia", "a48355@alunos.isel.pt"),
    Author(321, "Test Author", "email@alunos.pt")
))