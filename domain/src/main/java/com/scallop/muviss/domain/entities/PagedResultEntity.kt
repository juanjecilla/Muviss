package com.scallop.muviss.domain.entities

data class PagedResultEntity<T>(
    var page: Int?,
    var totalResults: Int?,
    var totalPages: Int?,
    var results: List<T> = emptyList()
)
