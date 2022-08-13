package com.tutorial.supercompose.themes.data

import com.tutorial.supercompose.navigation.Screen
import com.tutorial.supercompose.themes.ui.entity.ComposeTheme
import javax.inject.Inject

class ComposeThemesRepository @Inject constructor(

) {
    val composeThemesList = ComposeTheme.Chapter(
        id = 0F,
        name = "All themes",
        listOf(
            ComposeTheme.Chapter(
                1F, "QuickTests",
                listOf(
                    ComposeTheme.Topic(1.1F, "Test Coil", Screen.CoilScreen),
                )
            ),
            ComposeTheme.Topic(
                2F,
                "Creating First Jetpack Compose App",
                Screen.ComposeThemasScreen
            ),
            ComposeTheme.Topic(3F, "Row, Columns & Basic Sizing", Screen.RowColumnsBasicSizing),
            ComposeTheme.Topic(4F, "Modifiers", Screen.Modifiers),
            ComposeTheme.Topic(5F, "Creating an Image Card Composes", Screen.ImageCard),
            ComposeTheme.Topic(6F, "Styling text", Screen.StylingText),
            ComposeTheme.Topic(7F, "State", Screen.State),
            ComposeTheme.Topic(
                8F, "TextFields, Button $ Showing Snackbars",
                Screen.TextFields
            ),
            ComposeTheme.Chapter(
                9F, "Lists",
                listOf(
                    ComposeTheme.Topic(9.1F, "NotLazyColumn", Screen.NotLazyColumn),
                    ComposeTheme.Topic(9.2F, "LazyColumn", Screen.LazyColumn),
                )
            ),
            ComposeTheme.Topic(
                10F, "Constraint Layout", Screen.ConstraintLayoutComposeScreen
            ),
            ComposeTheme.Chapter(
                11F, "Side Effects & Effects Handlers",
                listOf(
                    ComposeTheme.Topic(
                        11.1F,
                        "OnBackPressedDispatcher",
                        Screen.OnBackPressedDispatcher
                    ),
                    ComposeTheme.Topic(11.2F, "CancelableSnackbar", Screen.CancelableSnackbar),
                    ComposeTheme.Topic(11.3F, "CancelableSnackbar2", Screen.CancelableSnackbar2),
                )
            ),
            ComposeTheme.Chapter(
                12F, "Simple animations",
                listOf(
                    ComposeTheme.Topic(12.1F, "TweenAnimation", Screen.TweenAnimation),
                    ComposeTheme.Topic(12.2F, "SpringAnimation", Screen.SpringAnimation),
                    ComposeTheme.Topic(12.3F, "KeyFramesAnimation", Screen.KeyFramesAnimation),
                    ComposeTheme.Topic(
                        12.4F, "InfiniteColorTransition", Screen.InfiniteColorTransition
                    ),
                    ComposeTheme.Topic(
                        12.5F, "PlaceHolderAnimation", Screen.PlaceHolderAnimationScreen
                    ),
                    ComposeTheme.Topic(12.6F, "Rotate animation", Screen.RotateAnimation),
                    ComposeTheme.Topic(
                        12.7F,
                        "Shimmer text animation",
                        Screen.ShimmerTextAnimation
                    ),
                )
            ),
            ComposeTheme.Topic(
                13F,
                "Animated Circular Progress Bar",
                Screen.AnimatedCircularProgressBar
            ),
            ComposeTheme.Topic(14F, "Draggable Music Knob", Screen.DraggableMusicKnob),
            ComposeTheme.Topic(15F, "Navigation", Screen.Navigation),
            ComposeTheme.Topic(16F, "AnimatedSplashScreen", Screen.AnimatedSplashScreen),
            ComposeTheme.Topic(
                17F, "Bottom Navigation With Badges", Screen.BottomNavigationWithBadges
            ),
            ComposeTheme.Topic(19F, "Complex Animations With MotionLayout", Screen.MotionLayout),
            ComposeTheme.Topic(20F, "Instagram Ui Profile", Screen.InstagramProfile),
            ComposeTheme.Topic(21F, "Pagination", Screen.PaginationScreen),
        )
    )

    fun getById(id: Float): ComposeTheme.Chapter? {
        return composeThemesList.getItem(id) as? ComposeTheme.Chapter
    }
}
