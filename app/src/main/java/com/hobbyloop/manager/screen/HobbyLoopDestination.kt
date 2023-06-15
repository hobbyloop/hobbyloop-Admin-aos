package com.hobbyloop.manager.screen

sealed class HobbyLoopDestination(
    val route: String
) {
    object Login : HobbyLoopDestination(LOGIN)

    object Home : HobbyLoopDestination(HOME)

    companion object {
        private const val LOGIN = "Login"
        private const val HOME = "Home"
    }
}
