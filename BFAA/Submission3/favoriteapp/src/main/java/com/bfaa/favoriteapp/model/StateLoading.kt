package com.bfaa.favoriteapp.model

class StateLoading<T>(val status: StatusType, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): StateLoading<T> {
            return StateLoading(StatusType.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): StateLoading<T> {
            return StateLoading(StatusType.ERROR, data, message)
        }
    }
}