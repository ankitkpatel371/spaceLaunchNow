package com.example.spacelaunchnow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spacelaunchnow.common.Constants.ASTRONAUTS_ID
import com.example.spacelaunchnow.presentation.astronauts_details.AstronautDetailsScreen
import com.example.spacelaunchnow.presentation.astronauts_list.AstronautsListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.AstronautsListScreen.route
    ) {
        composable(route = Screen.AstronautsListScreen.route) {
            AstronautsListScreen(navController)
        }
        composable(
            route = Screen.AstronautDetailsScreen.route + "/{$ASTRONAUTS_ID}",
            arguments = listOf(
                navArgument(name = ASTRONAUTS_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            AstronautDetailsScreen()
        }
    }
}