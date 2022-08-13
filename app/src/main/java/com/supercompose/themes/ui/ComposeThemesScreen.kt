package com.tutorial.supercompose.themes.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tutorial.supercompose.navigation.Screen
import com.tutorial.supercompose.themes.ui.entity.ComposeTheme

@Composable
fun ComposeThemesScreen(
    viewModel: ComposeThemasViewModel = viewModel(),
    navController: NavHostController,
    indexOfElement: String
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getComposeThemeById(indexOfElement.toFloatOrNull() ?: 0F)
    }

    val composeThemes = viewModel.composeThemesFlow.collectAsState()

    composeThemes.value?.let {
        Content(
            composeTheme = it,
            navController = navController
        )
    } ?: Error()
}

@Composable
fun Content(
    composeTheme: ComposeTheme.Chapter,
    navController: NavHostController
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = composeTheme.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        LazyColumn {
            items(items = composeTheme.composeThemes) { composeTheme ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            val route = when (composeTheme) {
                                is ComposeTheme.Chapter ->
                                    Screen.ComposeThemasScreen.putIndex(composeTheme.id)
                                is ComposeTheme.Topic -> composeTheme.screen.route
                            }
                            Log.d("DEBUG_TAG", "route = $route")
                            navController.navigate(route)
                        },
                    shape = MaterialTheme.shapes.medium,
                    elevation = 4.dp
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = composeTheme.name,
                        textAlign = TextAlign.Center,
                        color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun Error() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Smt went wrong(",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
