package com.example.camfeteriaapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.model.Producto
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel

@Composable
fun ProductoCard(producto: Producto, camfeteriaVM: CAMfeteriaViewModel) {

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
                        camfeteriaVM.agregarProducto(producto.nombre, producto.precio, context)
                    }) {
                        Text("+")
                    }
                }
            }
        }
    }
}