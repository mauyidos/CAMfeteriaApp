package com.example.camfeteriaapp.ui.productos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.R
import com.example.camfeteriaapp.CartManager
import androidx.compose.ui.platform.LocalContext

data class Producto(
    val nombre: String,
    val imagen: Int,
    val precio: Double
)

@Composable
fun DesayunosScreen(
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

@Composable
fun ProductoCard(producto: Producto) {

    val context = LocalContext.current
    var cantidad by remember { mutableStateOf(0) }

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column {

            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(producto.nombre, fontSize = 18.sp)

                Text("$${
                    producto.precio
                }", color = Color(0xFFD9A066))

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(onClick = {
                        if (cantidad > 0) cantidad--
                    }) {
                        Text("-")
                    }

                    Text(
                        "$cantidad",
                        modifier = Modifier.padding(16.dp)
                    )

                    Button(onClick = {
                        cantidad++
                        CartManager.agregarProducto(producto.nombre, producto.precio, context)
                    }) {
                        Text("+")
                    }
                }
            }
        }
    }
}