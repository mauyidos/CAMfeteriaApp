package com.example.camfeteriaapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.camfeteriaapp.data.ProductosData
import com.example.camfeteriaapp.model.ItemCarrito
import com.example.camfeteriaapp.model.Producto

class CafeteriaViewModel : ViewModel() {

    var productos by mutableStateOf(listOf<Producto>())
        private set

    var carrito = mutableStateListOf<ItemCarrito>()
        private set

    init {
        productos = ProductosData.listaProductos
    }

    fun agregarProducto(producto: Producto) {
        val itemExistente = carrito.find { it.producto.id == producto.id }

        if (itemExistente != null) {
            itemExistente.cantidad++
        } else {
            carrito.add(ItemCarrito(producto, 1))
        }
    }

    fun eliminarProducto(producto: Producto) {
        val item = carrito.find { it.producto.id == producto.id }
        item?.let {
            if (it.cantidad > 1) {
                it.cantidad--
            } else {
                carrito.remove(it)
            }
        }
    }

    fun calcularTotal(): Double {
        return carrito.sumOf { it.producto.precio * it.cantidad }
    }
}