package com.example.camfeteriaapp.ui.screens.menu.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.camfeteriaapp.R
import com.example.camfeteriaapp.model.Producto
import com.example.camfeteriaapp.ui.components.ProductoCard
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel
import androidx.compose.material.icons.filled.Search

@Composable
fun DesayunosScreen(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onAccountClick: () -> Unit,
    camfeteriaVM: CAMfeteriaViewModel
) {

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A2A44),
            Color(0xFF2F5D8C)
        )
    )

    val productos = listOf(
        Producto("Huevos revueltos", R.drawable.huevosrevueltos, 45.0),
        Producto("Molletes", R.drawable.molletes, 35.0),
        Producto("Omelette", R.drawable.omelettes, 50.0),
        Producto("Chilaquiles", R.drawable.chilaquiles, 55.0),
        Producto("Quesadillas", R.drawable.quesadillas, 30.0),
        Producto("Fruta con yogurt", R.drawable.frutaconyogurt, 40.0)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1A2A44)) {

                NavigationBarItem(
                    selected = false,
                    onClick = onMenuClick,
                    icon = { Icon(Icons.Default.RestaurantMenu, contentDescription = "Menú") },
                    label = { Text("Menú") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onSearchClick,
                    icon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    label = {
                        Text("Buscar")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onCartClick,
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
                    label = { Text("Carrito") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onAccountClick,
                    icon = { Icon(Icons.Default.Person, contentDescription = "Cuenta") },
                    label = { Text("Cuenta") }
                )
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
                .padding(16.dp)
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(productos.size) { index ->
                    ProductoCard(productos[index], camfeteriaVM)
                }
            }
        }
    }
}