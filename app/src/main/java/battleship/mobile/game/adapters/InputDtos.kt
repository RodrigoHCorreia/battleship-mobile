package battleship.mobile.game.adapters

import battleship.mobile.game.domain.BoardProposal
import battleship.mobile.game.domain.Coordinates

data class PositionDto(
    val x : Int,
    val y : Int
)

enum class DirectionDto { HORIZONTAL, VERTICAL }

data class RulesProposalDto(
    val placeholder : List<Int>
)

data class ShipProposalDto(
    val type : String,
    val position : PositionDto,
    val direction : DirectionDto
)

data class PlaceProposalDto(
    val list : List<ShipProposalDto>
)

data class ShotDto(
    val position : PositionDto
)

fun Coordinates.toPositionDto() =
    PositionDto(x, y)

fun BoardProposal.toPlaceProposalDto() =
    PlaceProposalDto(
        this.ships.map {
            ShipProposalDto(
                type = it.name,
                position = it.position.toPositionDto(),
                direction = if(it.horizontal) DirectionDto.HORIZONTAL else DirectionDto.VERTICAL
            )
        }
    )

