package com.example.camfeteriaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.camfeteriaapp.ui.screens.CarritoScreen
import com.example.camfeteriaapp.ui.screens.menu.MenuScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.BebidasCalientesScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.BebidasFriasScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.ComidasScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.DesayunosScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.EntradasScreen
import com.example.camfeteriaapp.ui.screens.menu.productos.PostresScreen
import com.example.camfeteriaapp.ui.screens.AuthScreen
import com.example.camfeteriaapp.ui.screens.CuentaScreen
import com.example.camfeteriaapp.ui.screens.RegisterScreen
import com.example.camfeteriaapp.ui.screens.SplashScreen
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel
import com.example.camfeteriaapp.ui.screens.BuscarScreen

@Composable
fun ScreenControl(camfeteriaVM: CAMfeteriaViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Cuenta.route) {
            CuentaScreen(
                camfeteriaVM = camfeteriaVM,
                onMenuClick = {
                    navController.navigate(Screen.Menu.route)
                },
                onCartClick = {
                    navController.navigate(Screen.Carrito.route)
                },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onAccountClick = {
                }
            )
        }

        composable(Screen.Splash.route) {
            SplashScreen(
                onFinish = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Auth.route) {
            AuthScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable("desayunos") {
            DesayunosScreen(
                onMenuClick = {
                    navController.navigate(Screen.Menu.route)
                },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = {
                    navController.navigate(Screen.Carrito.route)
                },
                onAccountClick = {
                    navController.navigate(Screen.Cuenta.route)
                },
                camfeteriaVM
            )
        }

        composable(Screen.Menu.route) {
            MenuScreen(
                onMenuClick = { },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) {
                    launchSingleTop = true
                }},
                onCategoryClick = { categoria ->

                    if (categoria == "Desayunos") {
                        navController.navigate("desayunos")
                    }

                    if (categoria == "Entradas") {
                        navController.navigate("entradas")
                    }

                    if (categoria == "Comidas") {
                        navController.navigate("comidas")
                    }

                    if (categoria == "Postres") {
                        navController.navigate("postres")
                    }

                    if (categoria == "Bebidas calientes") {
                        navController.navigate("bebidasCalientes")
                    }

                    if (categoria == "Bebidas frías") {
                        navController.navigate("bebidasFrias")
                    }

                    if (categoria == "Carrito") {
                        navController.navigate(Screen.Carrito.route)
                    }

                    if (categoria == "Cuenta") {
                        navController.navigate(Screen.Cuenta.route)
                    }

                }
            )
        }



        composable("entradas") {
            EntradasScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM
            )
        }

        composable("comidas") {
            ComidasScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM
            )
        }

        composable("postres") {
            PostresScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM
            )
        }

        composable("bebidasCalientes") {
            BebidasCalientesScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM
            )
        }

        composable("bebidasFrias") {
            BebidasFriasScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onCartClick = { navController.navigate(Screen.Carrito.route) },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM
            )
        }

        composable(Screen.Buscar.route) {
            BuscarScreen(
                camfeteriaVM = camfeteriaVM,

                onMenuClick = {
                    navController.navigate(Screen.Menu.route)
                },

                onSearchClick = { },

                onCartClick = {
                    navController.navigate(Screen.Carrito.route)
                },

                onAccountClick = {
                    navController.navigate(Screen.Cuenta.route)
                }
            )
        }

        composable(Screen.Carrito.route) {
            CarritoScreen(
                onMenuClick = { navController.navigate(Screen.Menu.route) },
                onSearchClick = {
                    navController.navigate(Screen.Buscar.route)
                },
                onAccountClick = { navController.navigate(Screen.Cuenta.route) },
                camfeteriaVM,
                navController
            )
        }

    }
}