package com.example.camfeteriaapp

import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // 🔐 validación
    val strength = when {
        password.length < 8 -> "Débil"
        password.any { it.isDigit() } && password.any { it.isLetter() } -> "Fuerte"
        else -> "Media"
    }

    val strengthColor = when (strength) {
        "Débil" -> Color.Red
        "Media" -> Color.Yellow
        else -> Color.Green
    }

    // ✨ animación entrada
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val alpha by animateFloatAsState(if (visible) 1f else 0f)
    val offsetY by animateDpAsState(if (visible) 0.dp else 40.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF5F3EF), Color(0xFFE7E3DC))
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    translationY = offsetY.toPx()
                },
            verticalArrangement = Arrangement.Center
        ) {

            Text("Crear cuenta", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(name, { name = it }, placeholder = { Text("Nombre completo") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(email, { email = it }, placeholder = { Text("Correo electrónico") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                password,
                { password = it },
                placeholder = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Nivel de seguridad: $strength", color = strengthColor)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                confirmPassword,
                { confirmPassword = it },
                placeholder = { Text("Confirmar contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (password == confirmPassword && password.length >= 8) {
                        userPrefs.saveUser(name, email, password)
                        Toast.makeText(context, "Registrado", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Error en datos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("REGISTRARSE")
            }
        }
    }
}