package ru.zhogin.clonehh27082024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zhogin.app.enterance.presentation.ui.navigation.EntranceFeature
import ru.zhogin.app.uikit.CloneHH27082024Theme
import ru.zhogin.clonehh27082024.presentation.ui.bottomnavbar.BottomBar
import ru.zhogin.clonehh27082024.presentation.ui.navigation.NavigationGraph
import ru.zhogin.clonehh27082024.presentation.ui.navigation.NavigationScreens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloneHH27082024Theme {
                val navController = rememberNavController()
                val btnItems = listOf(
                    NavigationScreens.SearchScreen.route,
                    NavigationScreens.FavouriteScreen.route,
                    NavigationScreens.ResponseScreen.route,
                    NavigationScreens.MessagesScreen.route,
                    NavigationScreens.ProfileScreen.route,
                )

                val btnBarAvailable  = navController.currentBackStackEntryAsState().value?.destination?.route in btnItems.map { it }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            counter = 3,
                            entered = btnBarAvailable
                        )
                    }
                ) { innerPadding ->
                    NavigationGraph(navController = navController, paddingValues = innerPadding)
                }
            }
        }
    }
}

