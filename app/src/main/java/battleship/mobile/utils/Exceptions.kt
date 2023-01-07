package battleship.mobile.utils

/**
 * Sum-Type to represent different exceptions that may occur in the Application. Thrown by services
 */
sealed class BattleshipException(
    message : String?
) : Exception(message)

class NotFoundException(message : String?) : Exception(message)
class ForbiddenException(message : String?) : Exception(message)
class UnexpectedException(message : String?) : Exception(message)
class UnauthorizedException(message : String?) : Exception(message)
class NoConnectionException(message : String?) : Exception(message)


