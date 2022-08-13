package com.tutorial.supercompose.pagination.presentation

import com.tutorial.supercompose.pagination.data.ListItem

data class PaginationScreenState(
    val isLoading: Boolean = false,
    val items: List<ListItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
