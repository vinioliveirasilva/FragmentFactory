package com.example.common.network

abstract class NetworkException(
    override val message: String,
    override val code: Int,
    override val cause: Throwable?
) : ClientException() {
    companion object {
        const val NETWORK_CONNECTIVITY_ERROR_MESSAGE =
            "Sem conexão. Verifique o wifi ou dados móveis e tente novamente em alguns instantes."
        const val NETWORK_GENERIC_ERROR_MESSAGE =
            "Não foi possível concluir. Estamos trabalhando para resolver. Tente novamente em alguns instantes."
    }
}

class BadURIException(cause: Throwable) :
    NetworkException(
        NETWORK_GENERIC_ERROR_MESSAGE, -1000, cause)

class HandshakeException(cause: Throwable) :
    NetworkException(
        NETWORK_GENERIC_ERROR_MESSAGE, -1200, cause)

class UnexpectedNetworkErrorException(cause: Throwable) :
    NetworkException(
        NETWORK_GENERIC_ERROR_MESSAGE, -1, cause)

class ConnectionTimeoutException(cause: Throwable) :
    NetworkException(
        NETWORK_GENERIC_ERROR_MESSAGE, -1001, cause)

abstract class NetworkConnectionException(code: Int, cause: Throwable?) :
    NetworkException(
        NETWORK_CONNECTIVITY_ERROR_MESSAGE, code, cause)

class CannotConnectToHostException(cause: Throwable) :
    NetworkConnectionException(-1004, cause)

class NoConnectivityException(cause: Throwable? = null) :
    NetworkConnectionException(-1009, cause)

class ThereIsNoRouteToHostException(cause: Throwable? = null) :
    NetworkConnectionException(-1003, cause)

class HostNotResolvedException(cause: Throwable) :
    NetworkConnectionException(-1006, cause)
