package by.minsk.polina_pasevina.cryptocurrency.network.utils

sealed class ClientException(message: String? = null) : RuntimeException(message)

class ConnectionFailedException(message: String? = null) : ClientException(message)

class BadRequestException(message: String? = null) : ClientException(message)