package com.adhish.onjuno.util

sealed class Result<out R> {
    data class Success<out T>(val result: T) : Result<T>()

    data class Error(
        val code: Int,
        val message: String? = null
    ) : Result<Nothing>()

}
