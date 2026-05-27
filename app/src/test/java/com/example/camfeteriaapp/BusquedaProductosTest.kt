package com.example.camfeteriaapp

import com.example.camfeteriaapp.data.ProductosData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BusquedaProductosTest {

    private fun buscarProductos(texto: String) =
        if (texto.isBlank()) {
            emptyList()
        } else {
            ProductosData.productos.filter { producto ->
                producto.nombre.contains(texto, ignoreCase = true)
            }
        }

    @Test
    fun buscarMo_encuentraMolletes() {
        val resultado = buscarProductos("Mo")

        assertTrue(
            resultado.any { it.nombre == "Molletes" }
        )
    }

    @Test
    fun buscarMinusculas_encuentraMolletes() {
        val resultado = buscarProductos("mo")

        assertTrue(
            resultado.any { it.nombre == "Molletes" }
        )
    }

    @Test
    fun buscarCafeMayusculas_encuentraCafeAmericano() {
        val resultado = buscarProductos("CAFÉ")

        assertTrue(
            resultado.any { it.nombre == "Café americano" }
        )
    }

    @Test
    fun buscarTextoInexistente_devuelveListaVacia() {
        val resultado = buscarProductos("Pizza")

        assertTrue(resultado.isEmpty())
    }

    @Test
    fun buscarTextoVacio_devuelveListaVacia() {
        val resultado = buscarProductos("")

        assertEquals(0, resultado.size)
    }
}