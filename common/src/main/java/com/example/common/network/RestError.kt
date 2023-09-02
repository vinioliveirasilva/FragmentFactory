package com.example.common.network

class RestError(val exception: Throwable) {

    val isConnectionError: Boolean
        get() = (exception is NetworkException && exception !is UnexpectedNetworkErrorException) ||
            exception is ConnectionTimeoutException
    val isInternetConnectionError: Boolean
        get() = exception is NoConnectivityException ||
            exception is ThereIsNoRouteToHostException ||
            exception is HostNotResolvedException
    val isTimeoutError: Boolean
        get() = exception is ConnectionTimeoutException
    val isUnknownError: Boolean
        get() = exception is UnexpectedNetworkErrorException
}
