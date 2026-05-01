package com.example.camfeteriaapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.camfeteriaapp.data.Usuario
import com.example.camfeteriaapp.data.UsuarioDatabase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsuarioDatabaseTest {

    private lateinit var db: UsuarioDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = UsuarioDatabase(context)
    }

    @Test
    fun insertarUsuario_correcto() {
        val usuario = Usuario(
            nombre = "Alejandro",
            correo = "ale@test.com",
            password = "abc12345"
        )

        val result = db.insertarUsuario(usuario)
        assertTrue(result != -1L)
    }

    @Test
    fun obtenerUsuario_porCorreo() {
        val usuario = Usuario(
            nombre = "Test",
            correo = "test@test.com",
            password = "12345678"
        )

        db.insertarUsuario(usuario)

        val resultado = db.obtenerUsuarioPorCorreo("test@test.com")

        assertNotNull(resultado)
        assertEquals("Test", resultado?.nombre)
    }
}