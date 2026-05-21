package com.example.camfeteriaapp

import androidx.compose.runtime.mutableStateListOf
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class CartItem(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

object CartManager {

    private const val PREFS_NAME = "carrito_prefs"
    private const val KEY_CART = "carrito"

    val items = mutableStateListOf<CartItem>()

    // 🔥 AGREGAR PRODUCTO
    fun agregarProducto(nombre: String, precio: Double, context: Context) {

        val existente = items.find { it.nombre == nombre }

        if (existente != null) {
            existente.cantidad++
        } else {
            items.add(CartItem(nombre, precio, 1))
        }

        guardarCarrito(context)
    }

    // 🔥 ACTUALIZAR CANTIDAD
    fun actualizarCantidad(item: CartItem, nuevaCantidad: Int, context: Context) {

        if (nuevaCantidad <= 0) {
            items.remove(item)
        } else {
            item.cantidad = nuevaCantidad
        }

        guardarCarrito(context)
    }

    // 🔥 TOTAL
    fun getTotal(): Double {
        return items.sumOf { it.precio * it.cantidad }
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
            val tipo = object : TypeToken<List<CartItem>>() {}.type
            val lista: List<CartItem> = Gson().fromJson(json, tipo)

            items.clear()
            items.addAll(lista)
        }
    }

    // 🔥 LIMPIAR
    fun limpiarCarrito(context: Context) {
        items.clear()
        guardarCarrito(context)
    }
}