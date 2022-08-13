package com.tutorial.supercompose.pagination.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorial.supercompose.pagination.data.ListItem

@Composable
fun PaginationScreen(viewModel: PaginationViewModel) {
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.loadNextItems()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(state.items.size) { index ->
            val item = state.items[index]
            if (index >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Item(item = item)
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    item: ListItem
) {
    Card(elevation = 8.dp) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.description)
        }
    }
}
