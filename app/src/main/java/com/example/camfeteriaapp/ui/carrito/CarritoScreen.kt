package com.example.camfeteriaapp.ui.carrito

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.CartManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*
@Composable
fun CarritoScreen(
    onMenuClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    val context = LocalContext.current
    val carrito = CartManager.items
    val total = CartManager.getTotal()

    LaunchedEffect(Unit) {
        CartManager.cargarCarrito(context)
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
            modifier = Modifier
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

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {

                items(carrito.size) { index ->

                    val item = carrito[index]

                    Card(
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(item.nombre)

                            Text("Precio: $${item.precio}")

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Button(onClick = {
                                    CartManager.actualizarCantidad(item, item.cantidad - 1, context)
                                }) {
                                    Text("-")
                                }

                                Text(
                                    "${item.cantidad}",
                                    modifier = Modifier.padding(16.dp)
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