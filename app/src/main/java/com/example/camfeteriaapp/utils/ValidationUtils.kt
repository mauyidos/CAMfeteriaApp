package com.example.camfeteriaapp.utils

object ValidationUtils {

    fun passwordSegura(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isLetter() } &&
                password.any { it.isDigit() }
    }
}
