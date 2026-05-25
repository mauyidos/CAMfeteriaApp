package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel

@Composable
fun CuentaScreen(
    camfeteriaVM: CAMfeteriaViewModel,
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

    var dineroRecibido by remember {
        mutableStateOf("")
    }

    val total = camfeteriaVM.total
    val recibido = dineroRecibido.toDoubleOrNull() ?: 0.0
    val cambio = recibido - total

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
                    onClick = onCartClick,
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
                    selected = true,
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

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Cuenta",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Total a pagar",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$$total",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = dineroRecibido,
                onValueChange = {
                    dineroRecibido = it
                },

                label = {
                    Text(
                        "Dinero recibido",
                        color = Color.Gray
                    )
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                modifier = Modifier.fillMaxWidth(),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    focusedBorderColor = Color(0xFF2F5D8C),
                    unfocusedBorderColor = Color.Gray,

                    cursorColor = Color(0xFF2F5D8C)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Cambio",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$$cambio",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}