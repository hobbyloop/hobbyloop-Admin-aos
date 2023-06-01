package com.hobbyloop.manager.screen

sealed class NavDestination(
    val route: String
) {
    object Login : NavDestination(LOGIN)

    object Home : NavDestination(HOME)

    companion object {
        private const val LOGIN = "Login"
        private const val HOME = "Home"
    }
}
