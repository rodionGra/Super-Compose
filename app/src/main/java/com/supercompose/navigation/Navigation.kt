package com.tutorial.supercompose.navigation

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
import com.supercompose.simple.ImageCardScreen
import com.supercompose.simple.custommodifier.CustomModifierScreen
import com.supercompose.simple.draggable.DraggableMusicKnobScreen
import com.supercompose.simple.signup.TextFieldStateManagerScreen
import com.tutorial.supercompose.difftest.CoilTestScreen
import com.tutorial.supercompose.home.HomeScreen
import com.tutorial.supercompose.instagram.InstagramProfileScreen
import com.tutorial.supercompose.pagination.presentation.PaginationScreen
import com.tutorial.supercompose.simple.CancelableSnackbar
import com.tutorial.supercompose.simple.CancelableSnackbar2
import com.tutorial.supercompose.simple.CircularProgressBarScreen
import com.tutorial.supercompose.simple.ConstraintLayoutComposeScreen
import com.tutorial.supercompose.simple.LazyColumnExample
import com.tutorial.supercompose.simple.Modifiers
import com.tutorial.supercompose.simple.MotionLayoutScreen
import com.tutorial.supercompose.simple.NotLazyColumn
import com.tutorial.supercompose.simple.RowColumnsBasicSizing
import com.tutorial.supercompose.simple.ShowTextScaffold
import com.tutorial.supercompose.simple.StateScreen
import com.tutorial.supercompose.simple.StylingTextScreen
import com.tutorial.supercompose.simple.animatedsplash.AnimatedSplashScreen
import com.tutorial.supercompose.simple.animation.InfiniteColorTransition
import com.tutorial.supercompose.simple.animation.KeyFramesAnimation
import com.tutorial.supercompose.simple.animation.PlaceholderAnimationScreen
import com.tutorial.supercompose.simple.animation.RotateAnimationScreen
import com.tutorial.supercompose.simple.animation.ShimmerTextAnimationScreen
import com.tutorial.supercompose.simple.animation.SpringAnimation
import com.tutorial.supercompose.simple.animation.TweenAnimation
import com.tutorial.supercompose.simple.bottomnavigation.BottomNavigationWithBadgesScreen
import com.tutorial.supercompose.simple.navigation.NavigationTheme
import com.tutorial.supercompose.themes.ui.ComposeThemesScreen

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
            route = Screen.NotLazyColumn.route
        ) {
            NotLazyColumn()
        }
        composable(
            route = Screen.LazyColumn.route
        ) {
            LazyColumnExample()
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
    }
}
