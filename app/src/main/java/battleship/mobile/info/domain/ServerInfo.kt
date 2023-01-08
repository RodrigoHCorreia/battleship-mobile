package battleship.mobile.info.domain

data class ServerAuthor(
    val id : Int,
    val name : String,
    val email : String
) {

    init {
        require(id >= 0) { "id must be >= 0" }
        require(name.isNotBlank()) { "name must not be blank" }
        require(email.isNotBlank()) { "email must not be blank" }
    }
}

data class ServerInfo(
    val version : String,
    val serverAuthors : List<ServerAuthor>
) {
    init {
        require(version.isNotBlank()) { "version is blank" }
        require(serverAuthors.isNotEmpty()) { "serverAuthors is empty" }
    }
}

