package com.example.camfeteriaapp

import android.content.Context

class UserPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("camfeteria_prefs", Context.MODE_PRIVATE)

    fun saveUser(name: String, email: String, password: String) {
        prefs.edit()
            .putString("name", name)
            .putString("email", email)
            .putString("password", password)
            .apply()
    }

    fun getUserEmail(): String? = prefs.getString("email", null)

    fun getUserPassword(): String? = prefs.getString("password", null)
}