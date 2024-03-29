package com.supercompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.supercompose.network.multiplebaseurl.ui.DynamicBaseUrlScreen
import com.supercompose.simple.CircularProgressBarScreen
import com.supercompose.simple.ImageCardScreen
import com.supercompose.simple.LazyColumnExample
import com.supercompose.simple.LazyRowExample
import com.supercompose.simple.Modifiers
import com.supercompose.simple.StateScreen
import com.supercompose.simple.StylingTextScreen
import com.supercompose.simple.custommodifier.CustomModifierScreen
import com.supercompose.simple.draggable.DraggableMusicKnobScreen
import com.supercompose.simple.signup.TextFieldStateManagerScreen
import com.supercompose.simple.swipetodismiss.SwipeToDismissScreen
import com.supercompose.themes.ui.ComposeThemesScreen
import com.tutorial.supercompose.difftest.CoilTestScreen
import com.tutorial.supercompose.home.HomeScreen
import com.tutorial.supercompose.instagram.InstagramProfileScreen
import com.tutorial.supercompose.pagination.presentation.PaginationScreen
import com.tutorial.supercompose.simple.*
import com.tutorial.supercompose.simple.animatedsplash.AnimatedSplashScreen
import com.tutorial.supercompose.simple.animation.*
import com.supercompose.simple.bottomnavigation.BottomNavigationWithBadgesScreen
import com.tutorial.supercompose.simple.navigation.NavigationTheme

@ExperimentalMotionApi
@ExperimentalAnimationApi
@Composable
fun NavigationComponent(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.ComposeThemasScreen.route + "/{${Screen.ComposeThemasScreen.COMPOSE_THEMAS_INDEX_PARAMETER}}",
            enterTransition = {
                slideInVertically(initialOffsetY = { 1800 })
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -1800 })
            },
            arguments = listOf(
                navArgument(Screen.ComposeThemasScreen.COMPOSE_THEMAS_INDEX_PARAMETER) {
                    type = NavType.StringType
                    defaultValue = Screen.ComposeThemasScreen.COMPOSE_THEMAS_INDEX_PARAMETER_DEFAULT
                    nullable = false
                }
            )
        ) { navBackStackEntry ->
            val index =
                navBackStackEntry.arguments?.getString(Screen.ComposeThemasScreen.COMPOSE_THEMAS_INDEX_PARAMETER)
                    ?: Screen.ComposeThemasScreen.COMPOSE_THEMAS_INDEX_PARAMETER_DEFAULT
            ComposeThemesScreen(hiltViewModel(), navController, index)
        }
        composable(
            route = Screen.RowColumnsBasicSizing.route
        ) {
            RowColumnsBasicSizing()
        }
        composable(
            route = Screen.LazyRow.route
        ) {
            LazyRowExample()
        }
        composable(
            route = Screen.LazyColumn.route
        ) {
            LazyColumnExample()
        }
        composable(
            route = Screen.SwipeToDismiss.route
        ) {
            SwipeToDismissScreen()
        }
        composable(
            route = Screen.ImageCard.route
        ) {
            ImageCardScreen()
        }
        composable(
            route = Screen.StylingText.route
        ) {
            StylingTextScreen()
        }
        composable(
            route = Screen.Modifiers.route
        ) {
            Modifiers()
        }
        composable(
            route = Screen.State.route
        ) {
            StateScreen()
        }
        composable(
            route = Screen.TextFields.route
        ) {
            ShowTextScaffold()
        }
        composable(
            route = Screen.OnBackPressedDispatcher.route
        ) {
            //todo MyComposable()
        }
        composable(
            route = Screen.CancelableSnackbar.route
        ) {
            CancelableSnackbar()
        }
        composable(
            route = Screen.CancelableSnackbar2.route
        ) {
            CancelableSnackbar2()
        }
        composable(
            route = Screen.Navigation.route
        ) {
            NavigationTheme()
        }
        composable(
            route = Screen.BottomNavigationWithBadges.route
        ) {
            BottomNavigationWithBadgesScreen()
        }
        composable(
            route = Screen.ConstraintLayoutComposeScreen.route
        ) {
            ConstraintLayoutComposeScreen()
        }
        composable(
            route = Screen.TweenAnimation.route
        ) {
            TweenAnimation()
        }
        composable(
            route = Screen.SpringAnimation.route
        ) {
            SpringAnimation()
        }
        composable(
            route = Screen.KeyFramesAnimation.route
        ) {
            KeyFramesAnimation()
        }
        composable(
            route = Screen.InfiniteColorTransition.route
        ) {
            InfiniteColorTransition()
        }
        composable(
            route = Screen.AnimatedCircularProgressBar.route
        ) {
            CircularProgressBarScreen()
        }
        composable(
            route = Screen.DraggableMusicKnob.route
        ) {
            DraggableMusicKnobScreen()
        }
        composable(
            route = Screen.MotionLayout.route
        ) {
            MotionLayoutScreen()
        }
        composable(
            route = Screen.CoilScreen.route
        ) {
            CoilTestScreen()
        }
        composable(
            route = Screen.RotateAnimation.route
        ) {
            RotateAnimationScreen()
        }
        composable(
            route = Screen.AnimatedSplashScreen.route
        ) {
            AnimatedSplashScreen()
        }
        composable(
            route = Screen.InstagramProfile.route
        ) {
            InstagramProfileScreen()
        }
        composable(
            route = Screen.PlaceHolderAnimationScreen.route
        ) {
            PlaceholderAnimationScreen()
        }
        composable(
            route = Screen.PaginationScreen.route
        ) {
            PaginationScreen(hiltViewModel())
        }
        composable(
            route = Screen.ShimmerTextAnimation.route
        ) {
            ShimmerTextAnimationScreen()
        }
        composable(
            route = Screen.CustomModifier.route
        ) {
            CustomModifierScreen()
        }
        composable(
            route = Screen.TextFieldStateManagerScreen.route
        ) {
            TextFieldStateManagerScreen(hiltViewModel())
        }
        composable(
            route = Screen.DynamicBaseUrlScreen.route
        ) {
            DynamicBaseUrlScreen(hiltViewModel())
        }
    }
}
