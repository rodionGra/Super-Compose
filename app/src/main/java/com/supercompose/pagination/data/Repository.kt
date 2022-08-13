package com.tutorial.supercompose.pagination.data

import kotlinx.coroutines.delay

class Repository {

    private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Title = $it",
            description = "Description = $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(4_000L)
        val startIndex = page * pageSize
        return if (startIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startIndex until startIndex + pageSize)
            )
        } else {
            Result.success(emptyList())
        }
    }
}
