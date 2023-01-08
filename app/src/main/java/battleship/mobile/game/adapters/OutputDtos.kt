package battleship.mobile.game.adapters

import battleship.mobile.game.domain.*
import battleship.mobile.utils.hypermedia.SirenEntity

enum class PlaceResultDto {
    PLACED, INVALID
}

enum class ShotResultDto {
    MISS, HIT, SUNK, INVALID
}

enum class GameStateDto {
    WAITING, PLANNING, FIGHTING, FINISHED;
    fun toGameState() =
        when(this) {
            WAITING -> GameState.WAITING
            PLANNING -> GameState.PLANNING
            FIGHTING -> GameState.FIGHTING
            FINISHED -> GameState.FINISHED
        }
}

data class BoardDtoProperties(
    val grid : List<String>
)

data class PlaceDtoProperties(
    val result : PlaceResultDto
)

data class ShotDtoProperties(
    val result : ShotResultDto,
    val ship : String?
)

data class GameDtoProperties(
    val id : Int,
    val ranked : Boolean,
    val opponent : Int?,
    val state : GameStateDto,
    val yourTurn : Boolean
) {
    fun toGameInfo() =
        GameInfo(
            id = id,
            state = state.toGameState(),
            ranked = ranked
        )
}

typealias BoardDto = SirenEntity<BoardDtoProperties>
val BoardDtoType = SirenEntity.getType<BoardDtoProperties>()
typealias GameDto = SirenEntity<GameDtoProperties>
val GameDtoType = SirenEntity.getType<GameDtoProperties>()


private const val CELL_CHAR_EMPTY = ' '
private const val CELL_CHAR_MISS = 'O'
private const val CELL_CHAR_SHIP = 'N'
private const val CELL_CHAR_HIT = 'x'
private const val CELL_CHAR_SUNK = 'X'

fun BoardDtoProperties.toBoard() =
    Board(grid.map { row ->
        row.map {
            when(it) {
                CELL_CHAR_EMPTY -> Cell.EMPTY
                CELL_CHAR_MISS -> Cell.MISS
                CELL_CHAR_SHIP -> Cell.SHIP
                CELL_CHAR_HIT -> Cell.HIT
                CELL_CHAR_SUNK -> Cell.SUNK
                else -> throw IllegalStateException()
            }
        }
    })

fun PlaceDtoProperties.toPlaceResult() =
    PlaceResult(
        when(result) {
            PlaceResultDto.PLACED -> PlaceResultStatus.PLACED
            PlaceResultDto.INVALID -> PlaceResultStatus.INVALID
        }
    )

fun ShotDtoProperties.toShotResult() =
    ShotResult(
        status = when(result) {
            ShotResultDto.MISS -> ShotResultStatus.MISS
            ShotResultDto.HIT -> ShotResultStatus.HIT
            ShotResultDto.SUNK -> ShotResultStatus.SUNK
            ShotResultDto.INVALID -> ShotResultStatus.INVALID
        },
        sunk = ship
    )
