package ru.zhogin.clonehh27082024.presentation.ui.bottomnavbar

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.TabText
import ru.zhogin.clonehh27082024.R

@Composable
internal fun BottomBar(
    navController : NavHostController,
    counter: Int,
    entered: Boolean,
) {
    NavigationBar(
        containerColor = Black
    ) {
        val items = listOf(
            BottomNavigationItem(
                title = "Поиск",
                icon = ImageVector.vectorResource(id = R.drawable.icon_search),
                route = "search_screen"
            ),
            BottomNavigationItem(
                title = "Избранное",
                icon = ImageVector.vectorResource(id = R.drawable.icon_favourite),
                route = "favourite_screen",
                badgeCount = counter,
            ),
            BottomNavigationItem(
                title = "Отклики",
                icon = ImageVector.vectorResource(id = R.drawable.icon_replies),
                route = "response_screen"
            ),
            BottomNavigationItem(
                title = "Сообщения",
                icon = ImageVector.vectorResource(id = R.drawable.icon_messages),
                route = "messages_screen"
            ),
            BottomNavigationItem(
                title = "Профиль",
                icon = ImageVector.vectorResource(id = R.drawable.icon_profile),
                route = "profile_screen"
            ),
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (entered) {
                        navController.navigate(item.route)
                    }
                },
                icon = {
                    BadgedBox(badge = {
                        if(item.badgeCount != null) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        }
                    }) {
                        Icon(imageVector = item.icon, contentDescription = item.title,
                            tint = if (currentRoute == item.route) Blue else Grey4)
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.TabText,
                        softWrap = false,
                        overflow = TextOverflow.Visible,
                        color = if (currentRoute == item.route) Blue else Grey4,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Black
                ),
                )
        }
    }
}