package battleship.mobile.info.domain

data class ServerAuthor(
    val id : Int,
    val name : String,
    val email : String
) {
    // TODO add requires
}

data class ServerInfo(
    val version : String,
    val serverAuthors : List<ServerAuthor>
) {
    // TODO add requires
}

