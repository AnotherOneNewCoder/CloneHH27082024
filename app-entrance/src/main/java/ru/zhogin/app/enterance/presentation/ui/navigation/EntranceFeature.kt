package ru.zhogin.app.enterance.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.zhogin.app.enterance.presentation.ui.navigation.model.NavigationEntranceScreen
import ru.zhogin.app.enterance.presentation.ui.screens.FirstEntrance
import ru.zhogin.app.enterance.presentation.ui.screens.SecondEntrance

@Composable
fun EntranceFeature(
    modifier: Modifier,
    entranceDone: () -> Unit,
) {
    val navController = rememberNavController()
    var email by rememberSaveable {
        mutableStateOf("")
    }
    NavHost(navController = navController, startDestination = NavigationEntranceScreen.FirstScreenEntrance.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )

        }) {
        composable(NavigationEntranceScreen.FirstScreenEntrance.route) {
            FirstEntrance(modifier = modifier, moveToSecondEntrance = {
                email = it
                navController.navigate(NavigationEntranceScreen.SecondScreenEntrance.route)
            })
        }
        composable(NavigationEntranceScreen.SecondScreenEntrance.route) {
            SecondEntrance(
                modifier = modifier,
                email = email,
                entranceDone = entranceDone
            )
        }
    }

}