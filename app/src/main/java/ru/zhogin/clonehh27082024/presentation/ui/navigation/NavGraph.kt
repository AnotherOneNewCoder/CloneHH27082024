package ru.zhogin.clonehh27082024.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.app.enterance.presentation.ui.navigation.EntranceFeature
import ru.zhogin.app.search.presentation.ui.SearchJobScreen
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Title1

@Composable
internal fun NavigationGraph(
    navController : NavHostController,
    paddingValues: PaddingValues,
) {


    NavHost(navController = navController, startDestination = NavigationScreens.EntranceFeature.route,
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
        composable(NavigationScreens.EntranceFeature.route) {
            EntranceFeature(modifier = Modifier.padding(paddingValues),
                entranceDone = {
                    navController.navigate(NavigationScreens.SearchScreen.route)
                })
        }
        composable(NavigationScreens.SearchScreen.route) {
            SearchJobScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(NavigationScreens.FavouriteScreen.route) {
            SearchScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(NavigationScreens.ResponseScreen.route) {
            SearchScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(NavigationScreens.MessagesScreen.route) {
            SearchScreen(modifier = Modifier.padding(paddingValues))

        }
        composable(NavigationScreens.ProfileScreen.route) {
            SearchScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Search Screen",
            style = MaterialTheme.typography.Title1)
    }
}