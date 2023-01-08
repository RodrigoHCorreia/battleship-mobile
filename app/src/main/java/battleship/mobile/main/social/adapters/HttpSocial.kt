package battleship.mobile.main.social.adapters

import battleship.mobile.main.social.domain.Social
import battleship.mobile.main.social.domain.User

class HttpSocial : Social {
    override suspend fun getRanking(page: Int): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUserByName(name : String): List<User> {
        TODO("Not yet implemented")
    }
}

