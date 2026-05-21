package com.example.camfeteriaapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.camfeteriaapp.model.Producto

class CafeteriaViewModel : ViewModel() {

    var productos by mutableStateOf(listOf<Producto>())
        private set
/*
    init {
        productos = ProductosData.listaProductos
    }

 */
}