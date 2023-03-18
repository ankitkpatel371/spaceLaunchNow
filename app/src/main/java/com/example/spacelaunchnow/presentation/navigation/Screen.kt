package com.example.spacelaunchnow.presentation.navigation

sealed class Screen(val route: String) {
    object AstronautsListScreen : Screen("astronautsListScreen")
    object AstronautDetailsScreen : Screen("AstronautDetailsScreen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
