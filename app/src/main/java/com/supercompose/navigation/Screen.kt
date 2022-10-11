package com.tutorial.supercompose.navigation

sealed class Screen(
    open val route: String
) {
    object HomeScreen : Screen("homeScreen")

    object ComposeThemasScreen : Screen("composeThemasScreen") {
        const val COMPOSE_THEMAS_INDEX_PARAMETER = "positionId"
        const val COMPOSE_THEMAS_INDEX_PARAMETER_DEFAULT = "0"

        //TODO think about this path
        /*fun putIndex(index: Float): String {
            return buildString {
                append(route)
                append("/$COMPOSE_THEMAS_INDEX_PARAMETER")
                append("=${index.toString()}")
            }
        }*/

        fun putIndex(index: Float): String {
            return buildString {
                append(route)
                append("/$index")
            }
        }
    }

    object RowColumnsBasicSizing : Screen("rowColumnsBasicSizing")

    object CoilScreen : Screen("coilScreen")

    object NotLazyColumn : Screen("notLazyColumn")
    object LazyColumn : Screen("lazyColumn")
    object ImageCard : Screen("imageCard")
    object StylingText : Screen("stylingText")
    object Modifiers : Screen("modifiers")
    object State : Screen("state")
    object TextFields : Screen("textFields")
    object OnBackPressedDispatcher : Screen("onBackPressedDispatcher")
    object CancelableSnackbar : Screen("CancelableSnackbar")
    object CancelableSnackbar2 : Screen("CancelableSnackbar2")
    object Navigation : Screen("navigaiton")
    object BottomNavigationWithBadges : Screen("bottomNavigationWithBadges")
    object ConstraintLayoutComposeScreen : Screen("constraintLayoutComposeScreen")
    object TweenAnimation : Screen("tweenAnimation")
    object SpringAnimation : Screen("springAnimation")
    object KeyFramesAnimation : Screen("keyFramesAnimation")
    object InfiniteColorTransition : Screen("infiniteColorTransition")
    object AnimatedCircularProgressBar : Screen("animatedCircularProgressBar")
    object DraggableMusicKnob : Screen("draggableMusicKnob")
    object MotionLayout : Screen("motionLayout")
    object RotateAnimation : Screen("RotateAnimation")
    object ShimmerTextAnimation : Screen("ShimmerTextAnimation")
    object AnimatedSplashScreen : Screen("AnimatedSplashScreen")
    object InstagramProfile : Screen("InstagramProfile")
    object PlaceHolderAnimationScreen : Screen("PlaceHolderAnimationScreen")
    object PaginationScreen : Screen("PaginationScreen")
    object CustomModifier : Screen("CustomModifier")
    object TextFieldStateManagerScreen : Screen("TextFieldStateManagerScreen")

    @Deprecated("all args should be not null")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}


sealed class FeatureScreen(
    override val route: String
) : Screen(route) {

    object Feature1 : FeatureScreen("feature1")

    object Feature2 : FeatureScreen("feature2")

    object Feature3 : FeatureScreen("feature3")

    object Feature4 : FeatureScreen("feature4")

    object Feature5 : FeatureScreen("feature5")


    companion object {
        fun getAllFeatures() = listOf(
            Feature1,
            Feature2,
            Feature3,
            Feature4,
            Feature5
        ).map { it.route }
    }
}
