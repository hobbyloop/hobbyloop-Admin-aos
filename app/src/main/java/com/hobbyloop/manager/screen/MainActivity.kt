package com.hobbyloop.manager.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hobbyloop.manager.screen.login.LoginScreen
import com.hobbyloop.manager.ui.theme.HobbyloopAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HobbyLoopAdminApp()
        }
    }
}

@Composable
private fun HobbyLoopAdminApp() {
    HobbyloopAdminTheme {
        NavHost(
            navController = rememberNavController(),
            startDestination = NavDestination.Login.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = NavDestination.Login.route) {
                LoginScreen()
            }

            composable(route = NavDestination.Home.route) {
                AdminScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HobbyLoopAdminAppPreview() {
    HobbyLoopAdminApp()
}
