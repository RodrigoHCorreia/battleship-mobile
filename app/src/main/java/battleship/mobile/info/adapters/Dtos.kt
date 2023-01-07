package battleship.mobile.info.adapters

import battleship.mobile.info.domain.ServerAuthor
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.utils.hypermedia.SirenEntity


data class AuthorDto(
    val name : String,
    val email : String,
    val id : Int,
){
    fun toServerAuthor() = ServerAuthor(
        id = id,
        name = name,
        email = email
    )
}

data class InfoDtoProperties(
    val version : String,
    val authors : List<AuthorDto>
)

typealias InfoDto = SirenEntity<InfoDtoProperties>
val InfoDtoType = SirenEntity.getType<InfoDtoProperties>()

fun InfoDtoProperties.toServerInfo() =
    ServerInfo(
        version,
        authors.map { it.toServerAuthor() }
    )
