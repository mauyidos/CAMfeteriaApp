package com.example.camfeteriaapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Register : Screen("register")
    object Carrito : Screen("carrito")
    object Productos : Screen("productos")
    object Menu : Screen("menu")
    object Cuenta : Screen("cuenta")
}