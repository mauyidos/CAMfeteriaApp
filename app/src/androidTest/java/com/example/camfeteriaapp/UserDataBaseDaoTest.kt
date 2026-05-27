package com.example.camfeteriaapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.camfeteriaapp.database.model.User
import com.example.camfeteriaapp.database.room.UserDataBase
import com.example.camfeteriaapp.database.room.UserDataBaseDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDataBaseDaoTest {

    private lateinit var database: UserDataBase
    private lateinit var dao: UserDataBaseDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.userDao()
    }

    @After
    fun cerrarBaseDeDatos() {
        database.close()
    }

    @Test
    fun insertarUsuario_yBuscarPorEmail() = runBlocking {
        val usuario = User(
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        dao.insert(usuario)

        val resultado = dao.getUserByEmail("carlos@test.com")

        assertNotNull(resultado)
        assertEquals("Carlos", resultado?.name)
        assertEquals("carlos@test.com", resultado?.email)
        assertEquals("abc12345", resultado?.password)
    }

    @Test
    fun buscarUsuarioInexistente_devuelveNull() = runBlocking {
        val resultado = dao.getUserByEmail("noexiste@test.com")

        assertNull(resultado)
    }

    @Test
    fun insertarVariosUsuarios_obtenerTodos() = runBlocking {
        dao.insert(
            User(
                name = "Carlos",
                email = "carlos@test.com",
                password = "abc12345"
            )
        )

        dao.insert(
            User(
                name = "Ana",
                email = "ana@test.com",
                password = "xyz12345"
            )
        )

        val usuarios = dao.getUser().first()

        assertEquals(2, usuarios.size)
    }

    @Test
    fun actualizarUsuario_cambiaDatos() = runBlocking {
        val usuario = User(
            id = 1,
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        dao.insert(usuario)

        val usuarioActualizado = User(
            id = 1,
            name = "Carlos García",
            email = "carlos@test.com",
            password = "nueva12345"
        )

        dao.update(usuarioActualizado)

        val resultado = dao.getUserByEmail("carlos@test.com")

        assertNotNull(resultado)
        assertEquals("Carlos García", resultado?.name)
        assertEquals("nueva12345", resultado?.password)
    }

    @Test
    fun eliminarUsuario_loQuitaDeLaBaseDeDatos() = runBlocking {
        val usuario = User(
            id = 1,
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        dao.insert(usuario)

        dao.delete(usuario)

        val resultado = dao.getUserByEmail("carlos@test.com")

        assertNull(resultado)
    }
}