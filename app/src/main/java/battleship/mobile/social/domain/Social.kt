package battleship.mobile.social.domain


/**
 * Used to represent the Social actions and information an application can make and get
 */
interface Social {
    suspend fun searchUserByName(name : String) : List<User>
    suspend fun getRanking(page : Int) : List<User>
}
