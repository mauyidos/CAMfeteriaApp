package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.camfeteriaapp.viewmodel.CafeteriaViewModel

@Composable
fun CarritoScreen(viewModel: CafeteriaViewModel) {

    val carrito = viewModel.carrito
    val total = viewModel.calcularTotal()

    Column(modifier = Modifier.padding(16.dp)) {

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
                Column {
                    Text(item.producto.nombre)
                    Text("Cantidad: ${item.cantidad}")
                }

                Column {
                    Text("$${item.producto.precio * item.cantidad}")

                    Button(onClick = {
                        viewModel.eliminarProducto(item.producto)
                    }) {
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