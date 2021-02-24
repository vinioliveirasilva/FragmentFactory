package com.example.common.network

import com.example.common.network.NetworkException.Companion.NETWORK_GENERIC_ERROR_MESSAGE
import retrofit2.HttpException

open class BusinessException(cause: HttpException, val responseVO: DefaultRestResponse) :
    ClientException(cause) {

    companion object {
        private const val UNDEFINED_ERROR = 99995
    }

    override val message: String
        get() = responseVO.errors.firstOrNull()?.message ?: NETWORK_GENERIC_ERROR_MESSAGE

    override val code: Int
        get() = responseVO.errors.firstOrNull()?.code?.toInt() ?: UNDEFINED_ERROR
}

class UnexpectedApiException(cause: HttpException) : ClientException(cause) {
    override val code: Int
        get() = -1011

    override val message: String?
        get() = NETWORK_GENERIC_ERROR_MESSAGE
}

class UnparsableErrorException(cause: Throwable) : ClientException(cause) {
    override val code: Int
        get() = -1015

    override val message: String?
        get() = NETWORK_GENERIC_ERROR_MESSAGE
}

class UnparsableResponseException(override val cause: Throwable) : ClientException(cause) {
    override val code: Int
        get() = -1017

    override val message: String?
        get() = NETWORK_GENERIC_ERROR_MESSAGE
}