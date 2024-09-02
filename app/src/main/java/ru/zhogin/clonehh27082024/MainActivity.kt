package ru.zhogin.clonehh27082024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zhogin.app.search.presentation.ui.state.StateVacancies
import ru.zhogin.app.search.presentation.viewmodel.SearchViewModel
import ru.zhogin.app.uikit.CloneHH27082024Theme
import ru.zhogin.clonehh27082024.presentation.ui.ErrorBox
import ru.zhogin.clonehh27082024.presentation.ui.LoadingBox
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
               // val searchViewModel: SearchViewModel = hiltViewModel()
                val searchViewModel: SearchViewModel = hiltViewModel()
//                val serverReply = searchViewModel.stateServerReply.collectAsState()
//                val counter = serverReply.value?.data?.vacancies?.filter {
//                    it.isFavorite
//                }?.size
                val offers = searchViewModel.stateOffers.collectAsState()
                val vacancies = searchViewModel.stateVacancies.collectAsState()
                val counter = vacancies.value.vacancies?.filter {
                    it.isFavorite
                }?.size
                val navController = rememberNavController()
                val btnItems = listOf(
                    NavigationScreens.SearchScreen.route,
                    NavigationScreens.FavouriteScreen.route,
                    NavigationScreens.ResponseScreen.route,
                    NavigationScreens.MessagesScreen.route,
                    NavigationScreens.ProfileScreen.route,
                    NavigationScreens.VacancyScreen.route
                )

                val btnBarAvailable =
                    navController.currentBackStackEntryAsState().value?.destination?.route in btnItems.map { it }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            counter = counter ?: 0,
                            entered = btnBarAvailable
                        )
                    }
                ) { innerPadding ->
                    when (vacancies.value) {
                        is StateVacancies.Success -> {
                            vacancies.value.vacancies?.let {
                                NavigationGraph(
                                    navController = navController,
                                    paddingValues = innerPadding,
                                    searchViewModel = searchViewModel,
                                    listOffers = offers.value.offers,
                                    listVacancies = it
                                )
                            }
                        }

                        is StateVacancies.Loading -> {
                            LoadingBox(paddingValues = innerPadding)
                        }

                        else -> ErrorBox(paddingValues = innerPadding)
                    }

//                    serverReply.value?.data?.let {
//                        NavigationGraph(
//                            navController = navController, paddingValues = innerPadding,
//                            searchViewModel = searchViewModel, serverReply = it
//                        )
//                    } ?: LoadingBox(paddingValues = innerPadding)
                }
            }
        }
    }
}

