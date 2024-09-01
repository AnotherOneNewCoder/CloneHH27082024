package ru.zhogin.app.enterance.presentation.ui.navigation.model

internal sealed class NavigationEntranceScreen(val route: String) {
    data object FirstScreenEntrance: NavigationEntranceScreen("first_entrance_screen")
    data object SecondScreenEntrance: NavigationEntranceScreen("second_entrance_screen")
}