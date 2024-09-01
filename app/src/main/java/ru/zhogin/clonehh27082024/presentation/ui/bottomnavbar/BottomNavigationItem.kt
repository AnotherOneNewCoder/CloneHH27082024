package ru.zhogin.clonehh27082024.presentation.ui.bottomnavbar

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int? = null,
)
