package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.CartManager

@Composable
fun CarritoScreen(
    onMenuClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    val context = LocalContext.current
    val carrito = CartManager.items
    var total by remember{mutableStateOf(0.0)}

    LaunchedEffect(Unit) {
        CartManager.cargarCarrito(context)
        total = CartManager.getTotal()
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = onMenuClick,
                    icon = { Icon(Icons.Default.RestaurantMenu, "") },
                    label = { Text("Menú") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.ShoppingCart, "") },
                    label = { Text("Carrito") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onAccountClick,
                    icon = { Icon(Icons.Default.Person, "") },
                    label = { Text("Cuenta") }
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(Color(0xFFF5F3EF))
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Carrito",
                fontSize = 28.sp,
                color = Color(0xFF1A2A44)
            )

            Spacer(modifier = Modifier.Companion.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.Companion.weight(1f)
            ) {

                items(carrito.size) { index ->

                    val item = carrito[index]

                    Card(
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.Companion.padding(16.dp)
                        ) {

                            Text(item.nombre)

                            Text("Precio: $${item.precio}")

                            Row(
                                verticalAlignment = Alignment.Companion.CenterVertically
                            ) {

                                Button(onClick = {
                                    CartManager.actualizarCantidad(item, item.cantidad - 1, context)
                                }) {
                                    Text("-")
                                }

                                Text(
                                    "${item.cantidad}",
                                    modifier = Modifier.Companion.padding(16.dp)
                                )

                                Button(onClick = {
                                    CartManager.actualizarCantidad(item, item.cantidad + 1, context)
                                }) {
                                    Text("+")
                                }
                            }

                            Text(
                                "Subtotal: $${item.precio * item.cantidad}"
                            )
                        }
                    }
                }
            }

            Button(onClick = {
                CartManager.limpiarCarrito(context)
            }) {
                Text("Vaciar carrito")
            }

            Text(
                text = "Total: $${"%.2f".format(total)}",
                fontSize = 22.sp,
                color = Color(0xFFD9A066)
            )
        }
    }
}