package battleship.mobile.services

import battleship.mobile.data.Rank

interface RankingService {

    /**
     * Used to identify how implementations SHOULD behave:
     * - [FORCE_REMOTE] is used to indicate that the operation MUST try to access
     * the remote data source
     * - [FORCE_LOCAL] is usd to indicate that the operation SHOULD only use the
     * the local version of the data, if available
     * - [AUTO] states that the selection of which data to use is left to the
     * implementation.
     */
    enum class Mode { FORCE_REMOTE, FORCE_LOCAL, AUTO }

    /**
     * Gets user ranking
     * @param username the user id
     * @param mode how the operation should behave. @see [Mode]
     * @return the ranking
     */
    //@Throws(IOException::class, UnexpectedResponseException::class)
    suspend fun fetchUserRank(username: String, mode: Mode = Mode.AUTO): Rank

    /**
     * Gets all rankings
     * @param mode how the operation should behave. @see [Mode]
     * @return the rankings
     */
    //@Throws(IOException::class, UnexpectedResponseException::class)
    suspend fun fetchRanking(mode: Mode = Mode.AUTO): List<Rank>

}
