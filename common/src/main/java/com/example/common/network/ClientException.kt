package com.example.common.network

abstract class ClientException : Exception {
    abstract val code: Int

    constructor(innerException: Throwable, message: String?) : super(message, innerException)
    constructor(innerException: Throwable) : super(innerException)
    constructor() : super()
}
