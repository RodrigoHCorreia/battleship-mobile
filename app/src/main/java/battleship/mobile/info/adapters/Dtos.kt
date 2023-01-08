package battleship.mobile.info.adapters

import battleship.mobile.info.domain.ServerAuthor
import battleship.mobile.info.domain.ServerInfo
import battleship.mobile.utils.hypermedia.SirenEntity


data class ServerAuthorDto(
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

data class ServerInfoDtoProperties(
    val version : String,
    val authors : List<ServerAuthorDto>
) {
    fun toServerInfo() = ServerInfo(
        version = version,
        serverAuthors = authors.map { it.toServerAuthor() }
    )
}

typealias ServerInfoDto = SirenEntity<ServerInfoDtoProperties>
val ServerInfoDtoType = SirenEntity.getType<ServerInfoDtoProperties>()

fun ServerInfo(dto: ServerInfoDto) : ServerInfo{
val properties = dto.properties
    require(properties != null) { "ServerInfoDto properties are null" }
    return properties.toServerInfo()
}

fun ServerInfoDtoProperties.toServerInfo() =
    ServerInfo(
        version,
        authors.map { it.toServerAuthor() }
    )
