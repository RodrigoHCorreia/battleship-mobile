package battleship.mobile.setup.domain

/**
 * Contract for repositories that hold Setups
 */
interface SetupRepository {

    // TODO idea - This is a variable list, perhaps it could be a value mutable list?
    var setups : List<Setup>

}
