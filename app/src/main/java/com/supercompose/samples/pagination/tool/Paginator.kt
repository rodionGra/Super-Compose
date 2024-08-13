package com.supercompose.samples.pagination.tool

interface Paginator<Item> {
    suspend fun loadNextItems()

    suspend fun reset()
}
