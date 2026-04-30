package com.example.camfeteriaapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.camfeteriaapp.ui.components.ProductoCard
import com.example.camfeteriaapp.viewmodel.CafeteriaViewModel

@Composable
fun ProductosScreen(viewModel: CafeteriaViewModel) {

    val productos = viewModel.productos

    LazyColumn {
        items(productos) { producto ->
            ProductoCard(
                producto = producto,
                onAgregar = {
                    viewModel.agregarProducto(producto)
                }
            )
        }
    }
}