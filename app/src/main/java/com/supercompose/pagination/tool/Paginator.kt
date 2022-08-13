package com.tutorial.supercompose.pagination.tool

interface Paginator<Item> {
    suspend fun loadNextItems()

    suspend fun reset()
}
