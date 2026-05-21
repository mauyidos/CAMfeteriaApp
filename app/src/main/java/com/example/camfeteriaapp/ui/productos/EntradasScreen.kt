package com.example.camfeteriaapp.ui.productos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.R

@Composable
fun EntradasScreen(
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
        Producto("Nachos", R.drawable.nachos, 40.0),
        Producto("Guacamole con chicharrón", R.drawable.guacamolechicharron, 50.0),
        Producto("Ensaladas", R.drawable.ensaladas, 35.0),
        Producto("Papas a la francesa", R.drawable.papasfrancesa, 30.0)
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