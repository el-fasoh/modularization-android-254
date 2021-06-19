package com.example.domain

import java.io.IOException
import java.net.SocketTimeoutException

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable) : Result<Nothing>() {
        var message: String? = exception.message

        val isNetworkError: Boolean get() = exception is IOException

        val isSocketTimeoutException: Boolean get() = exception is SocketTimeoutException
    }

    object Empty : Result<Nothing>()

    object Loading : Result<Nothing>()

    companion object {
        fun error(exception: Throwable) : Error {
            val error = Error(exception)

            when(exception) {
                is SocketTimeoutException -> error.apply { message = "Socket time out. Check connection" }
                else  -> error.apply { message = exception.message }
            }
            return error
        }

        /**
         * Return [Empty] if [list] is empty, otherwise return [Success].
         */
        fun <T> successOrEmpty(list: List<T>): Result<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}