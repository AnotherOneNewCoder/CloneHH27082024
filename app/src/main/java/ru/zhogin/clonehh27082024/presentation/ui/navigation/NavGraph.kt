package ru.zhogin.clonehh27082024.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zhogin.app.enterance.presentation.ui.navigation.EntranceFeature
import ru.zhogin.app.favourite.ui.screens.FavouriteScreenNew
import ru.zhogin.app.messages.presentation.ui.screens.MessagesScreen
import ru.zhogin.app.profile.presentation.ui.screens.ProfileScreen
import ru.zhogin.app.responses.presentation.ui.screens.ResponsesScreen
import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.screens.SearchJobScreenNew
import ru.zhogin.app.search.presentation.ui.screens.VacancyPageNew
import ru.zhogin.app.search.presentation.viewmodel.SearchViewModel

@Composable
internal fun NavigationGraph(
    navController : NavHostController,
    paddingValues: PaddingValues,
    searchViewModel: SearchViewModel,
    listOffers: List<Offer>?,
    listVacancies: List<Vacancy>,
) {



    var id by rememberSaveable {
        mutableStateOf("")
    }

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
            SearchJobScreenNew(
                modifier = Modifier.padding(paddingValues),
                listOffers = listOffers,
                listVacancies = listVacancies,
                showVacancyPage = {
                    id = it.id
                    navController.navigate(NavigationScreens.VacancyScreen.route)
                },
                showOrHideInFavourite = {
                    searchViewModel.showHideVacancy(it)
                }
            )
        }
        composable(NavigationScreens.FavouriteScreen.route) {
            FavouriteScreenNew(
                modifier = Modifier.padding(paddingValues),
                showVacancyPage = {
                    id = it.id
                    navController.navigate(NavigationScreens.VacancyScreen.route)
                }
            )
        }
        composable(NavigationScreens.ResponseScreen.route) {
            ResponsesScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(NavigationScreens.MessagesScreen.route) {
            MessagesScreen(modifier = Modifier.padding(paddingValues))

        }
        composable(NavigationScreens.ProfileScreen.route) {
            ProfileScreen(modifier = Modifier.padding(paddingValues))
        }
        composable(NavigationScreens.VacancyScreen.route) {
            VacancyPageNew(
                modifier = Modifier.padding(paddingValues),
                vacancy = listVacancies.last { it.id == id },
                onClickBack = {
                    navController.navigateUp()
                              },
                onAddToFavourite = { searchViewModel.showHideVacancy(it) })


        }
    }
}

