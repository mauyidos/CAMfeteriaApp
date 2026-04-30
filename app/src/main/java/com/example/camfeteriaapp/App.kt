package com.example.camfeteriaapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.camfeteriaapp.ui.screens.AuthScreen
import com.example.camfeteriaapp.ui.screens.CarritoScreen
import com.example.camfeteriaapp.ui.screens.ProductosScreen
import com.example.camfeteriaapp.ui.screens.RegisterScreen
import com.example.camfeteriaapp.ui.screens.SplashScreen
import com.example.camfeteriaapp.viewmodel.CafeteriaViewModel

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

        //
        composable("app") {
            App(navController)
        }
    }
}

@Composable
fun App(navController: NavController) {

    val viewModel = remember { CafeteriaViewModel() }
    var pantalla by remember { mutableStateOf("productos") }
    if (pantalla == "productos") {

        Column {
            ProductosScreen(viewModel)
            Button(onClick = { pantalla = "carrito" }) {
                Text("Ver carrito")
            }
        }

    } else {

        Column {
            CarritoScreen(viewModel)
            Button(onClick = { pantalla = "productos" }) {
                Text("Volver")
            }
        }
    }
}