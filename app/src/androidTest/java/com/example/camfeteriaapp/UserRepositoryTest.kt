package com.example.camfeteriaapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.camfeteriaapp.database.model.User
import com.example.camfeteriaapp.database.repository.UserRepository
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
class UserRepositoryTest {

    private lateinit var database: UserDataBase
    private lateinit var dao: UserDataBaseDao
    private lateinit var repository: UserRepository

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDataBase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.userDao()
        repository = UserRepository(dao)
    }

    @After
    fun cerrarBaseDeDatos() {
        database.close()
    }

    @Test
    fun addUser_guardaUsuario() = runBlocking {
        val usuario = User(
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        repository.addUser(usuario)

        val resultado = repository.getUserByEmail("carlos@test.com")

        assertNotNull(resultado)
        assertEquals("Carlos", resultado?.name)
        assertEquals("carlos@test.com", resultado?.email)
    }

    @Test
    fun getUserByEmail_devuelveUsuarioCorrecto() = runBlocking {
        val usuario1 = User(
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        val usuario2 = User(
            name = "Ana",
            email = "ana@test.com",
            password = "xyz12345"
        )

        repository.addUser(usuario1)
        repository.addUser(usuario2)

        val resultado = repository.getUserByEmail("ana@test.com")

        assertNotNull(resultado)
        assertEquals("Ana", resultado?.name)
        assertEquals("ana@test.com", resultado?.email)
    }

    @Test
    fun getAllUser_devuelveTodosLosUsuarios() = runBlocking {
        repository.addUser(
            User(
                name = "Carlos",
                email = "carlos@test.com",
                password = "abc12345"
            )
        )

        repository.addUser(
            User(
                name = "Ana",
                email = "ana@test.com",
                password = "xyz12345"
            )
        )

        val usuarios = repository.getAllUser().first()

        assertEquals(2, usuarios.size)
    }

    @Test
    fun updateUser_actualizaDatos() = runBlocking {
        val usuario = User(
            id = 1,
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        repository.addUser(usuario)

        val usuarioActualizado = User(
            id = 1,
            name = "Carlos García",
            email = "carlos@test.com",
            password = "nueva12345"
        )

        repository.updateUser(usuarioActualizado)

        val resultado = repository.getUserByEmail("carlos@test.com")

        assertNotNull(resultado)
        assertEquals("Carlos García", resultado?.name)
        assertEquals("nueva12345", resultado?.password)
    }

    @Test
    fun deleteUser_eliminaUsuario() = runBlocking {
        val usuario = User(
            id = 1,
            name = "Carlos",
            email = "carlos@test.com",
            password = "abc12345"
        )

        repository.addUser(usuario)

        repository.deleteUser(usuario)

        val resultado = repository.getUserByEmail("carlos@test.com")

        assertNull(resultado)
    }
}