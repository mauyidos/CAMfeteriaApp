package com.example.camfeteriaapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsuarioDatabase(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                correo TEXT UNIQUE,
                password TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(usuario: Usuario): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put("nombre", usuario.nombre)
            put("correo", usuario.correo)
            put("password", usuario.password)
        }

        return db.insert("usuarios", null, values)
    }

    fun obtenerUsuarioPorCorreo(correo: String): Usuario? {
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo = ?",
            arrayOf(correo)
        )

        return if (cursor.moveToFirst()) {
            val usuario = Usuario(
                id = cursor.getInt(0),
                nombre = cursor.getString(1),
                correo = cursor.getString(2),
                password = cursor.getString(3)
            )
            cursor.close()
            usuario
        } else {
            cursor.close()
            null
        }
    }
}
