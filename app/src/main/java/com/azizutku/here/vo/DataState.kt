package com.azizutku.here.vo

sealed class DataState<out T> {

    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val throwable: Throwable): DataState<Nothing>()
    object Loading: DataState<Nothing>()
    object None: DataState<Nothing>()

}
