@file:OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)

package com.supercompose.simple.swipetodismiss

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwipeToDismissScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(content = {
            items(count = 100, itemContent = { item ->
                val currentItem by rememberUpdatedState(item)
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        //todo remove current item in viewModel
                        true
                    }
                )
                /*
                *state — this is needed to detect the state of SwipeToDelete, i.e. is it moving from Start to End or End to Start or Default (no movement)
                *background — when we are swiping away the item, this will render how the exposed background looks.
                 I personally think this can be made optional, with a blank body, but looks like it is needed.
                *dismissContent — this is where we place the original Row item of the LazyColumn
                */
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .animateItemPlacement(),
                    background = {
                        SwipeBackground(dismissState)
                    },
                    dismissContent = {
                        SwipeToDismissListItem(item = item)
                    }
                )
            })
        })
    }
}

@Composable
private fun SwipeToDismissListItem(item: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "Item #$item"
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return

    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> Color.Red
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Done
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            modifier = Modifier.scale(scale)
        )
    }
}
