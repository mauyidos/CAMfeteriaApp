package com.example.camfeteriaapp

import com.example.camfeteriaapp.model.Producto
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CAMfeteriaViewModelTest {

    private lateinit var viewModel: CAMfeteriaViewModel

    @Before
    fun setup() {
        viewModel = CAMfeteriaViewModel()
    }

    @Test
    fun carritoInicial_estaVacio() {
        assertTrue(viewModel.items.isEmpty())
    }

    @Test
    fun totalInicial_esCero() {
        assertEquals(0.0, viewModel.total, 0.0)
    }

    @Test
    fun getTotal_calculaTotalCorrectamente() {
        viewModel.items.add(
            Producto(
                nombre = "Café americano",
                imagen = 0,
                precio = 20.0,
                cantidad = 2
            )
        )

        viewModel.items.add(
            Producto(
                nombre = "Molletes",
                imagen = 0,
                precio = 35.0,
                cantidad = 1
            )
        )

        viewModel.getTotal()

        assertEquals(75.0, viewModel.total, 0.0)
    }

    @Test
    fun getTotal_conCarritoVacio_daCero() {
        viewModel.getTotal()

        assertEquals(0.0, viewModel.total, 0.0)
    }

    @Test
    fun items_puedeAgregarProductoManualmente() {
        val producto = Producto(
            nombre = "Capuccino",
            imagen = 0,
            precio = 30.0,
            cantidad = 1
        )

        viewModel.items.add(producto)

        assertEquals(1, viewModel.items.size)
        assertEquals("Capuccino", viewModel.items[0].nombre)
        assertEquals(30.0, viewModel.items[0].precio, 0.0)
        assertEquals(1, viewModel.items[0].cantidad)
    }
}