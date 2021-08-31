package com.scallop.muviss.domain.entities

sealed class ResultWrapperEntity<out T> {
    data class Success<out T>(val value: T) : ResultWrapperEntity<T>()
    data class GenericError(val code: Int? = null, val exception: Exception) :
        ResultWrapperEntity<Nothing>()

    object NetworkError : ResultWrapperEntity<Nothing>()
}