package com.scallop.muviss.data.entitites

sealed class ResultWrapperData<out T> {
    data class Success<out T>(val value: T) : ResultWrapperData<T>()
    data class GenericError(val code: Int? = null, val exception: Exception) :
        ResultWrapperData<Nothing>()

    object NetworkError : ResultWrapperData<Nothing>()
}
