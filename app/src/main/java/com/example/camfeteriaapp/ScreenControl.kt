package com.example.camfeteriaapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.camfeteriaapp.ui.screens.App
import com.example.camfeteriaapp.ui.screens.AuthScreen
import com.example.camfeteriaapp.ui.screens.RegisterScreen
import com.example.camfeteriaapp.ui.screens.SplashScreen

@Composable
fun ScreenControl() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        //Splash screen
        composable("splash") {
            SplashScreen(
                onFinish = {
                    navController.navigate("auth") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        //Authentication screen
        composable("auth") {
            AuthScreen(navController)
        }

        //Register screen
        composable("register") {
            RegisterScreen(navController)
        }

        //App screen
        composable("app") {
            App(navController)
        }
    }
}