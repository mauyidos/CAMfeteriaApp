package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camfeteriaapp.ui.components.CarritoCard
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel
import androidx.compose.material.icons.filled.Search

@Composable
fun CarritoScreen(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAccountClick: () -> Unit,
    camfeteriaVM: CAMfeteriaViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val carrito = camfeteriaVM.items

    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A2A44),
            Color(0xFF2F5D8C)
        )
    )

    LaunchedEffect(Unit) {
        camfeteriaVM.cargarCarrito(context)
        camfeteriaVM.getTotal()
    }

    Scaffold(

        containerColor = Color.Transparent,

        bottomBar = {

            NavigationBar(
                containerColor = Color(0xFF1A2A44)
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = onMenuClick,
                    icon = {
                        Icon(
                            Icons.Default.RestaurantMenu,
                            contentDescription = "Menú"
                        )
                    },
                    label = {
                        Text("Menú")
                    }
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
                    selected = true,
                    onClick = { },
                    icon = {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                    },
                    label = {
                        Text("Carrito")
                    }
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
                    label = {
                        Text("Cuenta")
                    }
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Carrito",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.weight(1f)
            ) {

                items(carrito) { item ->

                    Card(
                        shape = MaterialTheme.shapes.large,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        CarritoCard(
                            context,
                            item,
                            camfeteriaVM,
                            navController
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Resumen",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A2A44)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$${"%.2f".format(camfeteriaVM.total)}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD9A066)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            camfeteriaVM.limpiarCarrito(context)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Vaciar carrito",
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}