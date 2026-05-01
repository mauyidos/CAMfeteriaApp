package com.example.camfeteriaapp

import com.example.camfeteriaapp.utils.ValidationUtils
import org.junit.Assert.*
import org.junit.Test

class PasswordTest {

    @Test
    fun passwordValida() {
        assertTrue(ValidationUtils.passwordSegura("abc12345"))
    }

    @Test
    fun passwordInvalida() {
        assertFalse(ValidationUtils.passwordSegura("123"))
    }
}

