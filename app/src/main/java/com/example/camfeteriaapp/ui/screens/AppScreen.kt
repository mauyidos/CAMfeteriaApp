package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.camfeteriaapp.ui.components.ProductoCard
import com.example.camfeteriaapp.viewmodel.CafeteriaViewModel

@Composable
fun App(navController: NavController) {

    val viewModel = remember { CafeteriaViewModel() }
    var pantalla by remember { mutableStateOf("productos") }
    if (pantalla == "productos") {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProductosScreen(viewModel)
            Button(onClick = { pantalla = "carrito" }) {
                Text("Ver carrito")
            }
        }

    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarritoScreen(viewModel)
            Button(onClick = { pantalla = "productos" }) {
                Text("Volver")
            }
        }
    }
}

@Composable
fun ProductosScreen(viewModel: CafeteriaViewModel) {

    val productos = viewModel.productos

    LazyColumn {
        items(productos) {
            producto ->
            ProductoCard(
                producto = producto,
                onAgregar = {
                    viewModel.agregarProducto(producto)
                }
            )
        }
    }
}

@Composable
fun CarritoScreen(viewModel: CafeteriaViewModel) {

    val carrito = viewModel.carrito
    val total = viewModel.calcularTotal()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            "Carrito",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        carrito.forEach { item ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(item.producto.nombre)
                    Text("Cantidad: ${item.cantidad}")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("$${item.producto.precio * item.cantidad}")

                    Button(
                        onClick = {
                            viewModel.eliminarProducto(item.producto)
                        }
                    ) {
                        Text("Quitar")
                    }
                }
            }

            Divider()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Total: $${total}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}