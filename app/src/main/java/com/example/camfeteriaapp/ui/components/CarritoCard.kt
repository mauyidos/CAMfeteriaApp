package com.example.camfeteriaapp.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.example.camfeteriaapp.model.Producto
import com.example.camfeteriaapp.navigation.Screen
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel

@Composable
fun CarritoCard(context: Context, item: Producto, camfeteriaVM: CAMfeteriaViewModel, navController: NavController) {
    var cantidad by remember { mutableStateOf(item.cantidad) }
    var subtotal by remember { mutableStateOf(0.0) }

    subtotal = cantidad * item.precio

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
                    cantidad--
                    if (cantidad <= 0) {
                        navController.navigate(Screen.Carrito.route)
                    }
                    subtotal = cantidad * item.precio
                    camfeteriaVM.actualizarCantidad(item, item.cantidad - 1, context)
                }) {
                    Text("-")
                }

                Text(
                    "${cantidad}",
                    modifier = Modifier.Companion.padding(16.dp)
                )

                Button(onClick = {
                    cantidad++
                    subtotal = cantidad * item.precio
                    camfeteriaVM.actualizarCantidad(item, item.cantidad + 1, context)
                }) {
                    Text("+")
                }
            }

            Text(
                "Subtotal: $${subtotal}"
            )
        }
    }
}