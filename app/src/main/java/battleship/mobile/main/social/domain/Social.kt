package battleship.mobile.main.social.domain


interface Social {

    suspend fun getRanking(page : Int) : List<User>
    suspend fun getByName(): User?

}
