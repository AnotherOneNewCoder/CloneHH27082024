package ru.zhogin.clonehh27082024.presentation.ui.navigation

sealed class NavigationScreens(val route: String) {
    data object EntranceFeature: NavigationScreens("entrance_feature")
    data object SearchScreen: NavigationScreens("search_screen")
    data object FavouriteScreen: NavigationScreens("favourite_screen")
    data object ResponseScreen: NavigationScreens("response_screen")
    data object MessagesScreen: NavigationScreens("messages_screen")
    data object ProfileScreen: NavigationScreens("profile_screen")
    data object VacancyScreen :NavigationScreens("vacancy_screen")
}