package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.data.ProductosData
import com.example.camfeteriaapp.ui.components.ProductoCard
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel

@Composable
fun BuscarScreen(
    camfeteriaVM: CAMfeteriaViewModel,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }

    val productosFiltrados = if (textoBusqueda.isBlank()) {
        emptyList()
    } else {
        ProductosData.productos.filter { producto ->
            producto.nombre.contains(textoBusqueda, ignoreCase = true)
        }
    }

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A2A44),
            Color(0xFF2F5D8C)
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1A2A44)) {
                NavigationBarItem(
                    selected = false,
                    onClick = onMenuClick,
                    icon = {
                        Icon(
                            Icons.Default.RestaurantMenu,
                            contentDescription = "Menú"
                        )
                    },
                    label = { Text("Menú") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = onSearchClick,
                    icon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    label = { Text("Buscar") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onCartClick,
                    icon = {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                    },
                    label = { Text("Carrito") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onAccountClick,
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Cuenta"
                        )
                    },
                    label = { Text("Cuenta") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Buscar producto",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Ejemplo: Molletes")
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (textoBusqueda.isBlank()) {
                Text(
                    text = "Escribe el nombre de un producto para buscarlo.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            } else if (productosFiltrados.isEmpty()) {
                Text(
                    text = "No se encontraron productos.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productosFiltrados) { producto ->
                        ProductoCard(
                            producto = producto,
                            camfeteriaVM = camfeteriaVM
                        )
                    }
                }
            }
        }
    }
}