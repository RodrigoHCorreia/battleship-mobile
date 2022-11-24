package battleship.mobile.data

import battleship.mobile.Author

data class Info(
    val version: String,
    val authors: List<Author>,
)

val appInfo = Info(
    version = "0.1 ALPHA", authors = listOf(
        Author(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
        Author(48355, "Rodrigo Correia", "a48355@alunos.isel.pt"),
        Author(46587, "Gonçalo Silva", "a46587@alunos.isel.pt")
    )
)