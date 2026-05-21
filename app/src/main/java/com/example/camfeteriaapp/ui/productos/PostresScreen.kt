package com.example.camfeteriaapp.ui.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.camfeteriaapp.R

@Composable
fun PostresScreen(
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit,
    onAccountClick: () -> Unit
) {

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A2A44),
            Color(0xFF2F5D8C)
        )
    )

    val productos = listOf(
        Producto("Pastel de chocolate", R.drawable.pastelchocolate, 45.0),
        Producto("Helado", R.drawable.helado, 25.0),
        Producto("Crepas", R.drawable.crepas, 40.0),
        Producto("Pan francés", R.drawable.panfrances, 35.0),
        Producto("Arroz con leche", R.drawable.arrozleche, 30.0),
        Producto("Galletas con chocolate", R.drawable.galletaschocolate, 20.0),
        Producto("Hot cakes", R.drawable.hotcake, 35.0)
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
                    ProductoCard(productos[index])
                }
            }
        }
    }
}