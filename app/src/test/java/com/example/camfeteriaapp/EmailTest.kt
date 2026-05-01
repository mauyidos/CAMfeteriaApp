package com.example.camfeteriaapp

import org.junit.Assert.*
import org.junit.Test

class EmailTest {

    private fun esEmailValido(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    @Test
    fun emailCorrecto() {
        assertTrue(esEmailValido("test@email.com"))
    }

    @Test
    fun emailIncorrecto() {
        assertFalse(esEmailValido("correo"))
    }
}
