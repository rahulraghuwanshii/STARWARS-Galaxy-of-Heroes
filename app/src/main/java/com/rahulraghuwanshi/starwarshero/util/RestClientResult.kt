package com.rahulraghuwanshi.starwarshero.util

data class RestClientResult<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val errorCode: String? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NONE
    }

    companion object {
        fun <T> success(data: T): RestClientResult<T> {
            return RestClientResult(Status.SUCCESS, data, null)
        }

        fun <T> loading(): RestClientResult<T> {
            return RestClientResult(Status.LOADING)
        }

        fun <T> error(
            message: String,
            errorCode: String? = null
        ): RestClientResult<T> {
            return RestClientResult(
                Status.ERROR,
                message = message,
                errorCode = errorCode
            )
        }

        fun <T> none(): RestClientResult<T> {
            return RestClientResult(Status.NONE)
        }
    }
}