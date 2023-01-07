package battleship.mobile.game.domain

data class ShipProposal(
    val name : String,
    val position : Coordinates,
    val horizontal : Boolean // Otherwise its Vertical! - this a crime against humanity, enum it..?
)

data class BoardProposal(
    val ships : List<ShipProposal> = emptyList()
)
