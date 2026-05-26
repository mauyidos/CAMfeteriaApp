package com.example.camfeteriaapp.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.camfeteriaapp.model.Producto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val PREFS_NAME = "carrito_prefs"
private const val KEY_CART = "carrito"

@HiltViewModel
class CAMfeteriaViewModel @Inject constructor() : ViewModel() {
    var productos by mutableStateOf(listOf<Producto>())
        private set

    val items = mutableStateListOf<Producto>()

    var total by mutableStateOf(0.0)
        public set

    // 🔥 AGREGAR PRODUCTO
    fun agregarProducto(nombre: String, precio: Double, context: Context) {

        val existente = items.find { it.nombre == nombre }

        if (existente != null) {
            existente.cantidad++
        } else {
            items.add(Producto(nombre, 0, precio, 1))
        }

        guardarCarrito(context)
    }

    // 🔥 ACTUALIZAR CANTIDAD
    fun actualizarCantidad(item: Producto, nuevaCantidad: Int, context: Context) {

        if (nuevaCantidad <= 0) {
            items.remove(item)
        } else {
            item.cantidad = nuevaCantidad
        }

        getTotal()

        guardarCarrito(context)
    }

    // 🔥 TOTAL
    fun getTotal() {
        total = items.sumOf { it.precio * it.cantidad }
    }

    // 🔥 GUARDAR
    fun guardarCarrito(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(items)
        prefs.edit().putString(KEY_CART, json).apply()
    }

    // 🔥 CARGAR
    fun cargarCarrito(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_CART, null)

        if (json != null) {
            val tipo = object : TypeToken<List<Producto>>() {}.type
            val lista: List<Producto> = Gson().fromJson(json, tipo)

            items.clear()
            items.addAll(lista)
        }
    }

    // 🔥 LIMPIAR
    fun limpiarCarrito(context: Context) {
        items.clear()
        getTotal()
        guardarCarrito(context)
    }
}