package com.example.camfeteriaapp

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Register : Screen("register")
}