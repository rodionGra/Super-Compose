package com.tutorial.supercompose.simple.bottomnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    navItemsList: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        navItemsList.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (it.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(text = it.badgeCount.toString())
                                }
                            ) {
                                Icon(imageVector = it.icon, contentDescription = it.name)
                            }
                        } else {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.name
                            )
                        }
                        if (selected) {
                            Text(
                                text = it.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}


//test custom. Can be removed
@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun TEst() {
    BottomNavigation(
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { },
            selectedContentColor = Color.Green,
            unselectedContentColor = Color.Gray,
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    BadgedBox(
                        badge = {
                            Text(text = "8")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                    Text(
                        text = "Home screen",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
            }
        )
    }
    BadgedBox(
        badge = {
            Text(text = "3")
        }
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            tint = Color.Blue,
            contentDescription = "home"
        )
    }
}
