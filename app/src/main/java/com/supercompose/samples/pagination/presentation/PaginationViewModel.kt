package com.supercompose.samples.pagination.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supercompose.samples.pagination.data.Repository
import com.supercompose.samples.pagination.tool.DefaultPaginator
import kotlinx.coroutines.launch

class PaginationViewModel : ViewModel() {

    init {
        //loadNextItems()
    }

    private val repository = Repository() //todo inject

    var state by mutableStateOf(PaginationScreenState())

    private val paginator = DefaultPaginator(
        initialKey = 0,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getItems(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state =
                state.copy(items = state.items + items, page = newKey, endReached = items.isEmpty())
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}
