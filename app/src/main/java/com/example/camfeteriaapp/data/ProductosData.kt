package com.example.camfeteriaapp.data

import com.example.camfeteriaapp.model.Producto
import com.example.camfeteriaapp.R


object ProductosData {
    val listaProductos = listOf(
        Producto(1, "Café Americano", 25.0, R.drawable.cafe_americano),
        Producto(2, "Capuccino", 40.0, R.drawable.cappuccino),
        Producto(3, "Pan dulce", 20.0, R.drawable.concha)
    )
}