package com.example.camfeteriaapp.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camfeteriaapp.R
import com.example.camfeteriaapp.UserPreferences

@Composable
fun AuthScreen(navController: NavController) {

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    // 🧠 Estados de inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // 👁️ mostrar/ocultar contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    // 🎯 interacción botón
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // 🎨 animación de color botón
    val buttonColor by animateColorAsState(
        targetValue = if (isPressed)
            Color(0xFFD9A066)
        else
            Color(0xFFA47148),
        label = ""
    )

    // ✨ Animación de entrada
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(1000)
    )

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 40.dp,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F3EF),
                        Color(0xFFE7E3DC)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    translationY = offsetY.toPx()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ☕ Logo animado
            Image(
                painter = painterResource(id = R.drawable.logocafeteria),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(140.dp)
                    .scale(alpha) // pequeño efecto zoom
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 📧 INPUT EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Correo electrónico") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔒 INPUT PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Contraseña") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Text(if (passwordVisible) "Ocultar" else "Ver")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🔘 BOTÓN LOGIN
            Button(
                onClick = {
                    val savedEmail = userPrefs.getUserEmail()
                    val savedPassword = userPrefs.getUserPassword()

                    if (email == savedEmail && password == savedPassword) {
                        Toast.makeText(context, "Login correcto", Toast.LENGTH_SHORT).show()
                        navController.navigate("app")
                    } else {
                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                },
                interactionSource = interactionSource,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .shadow(6.dp, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color(0xFFF5F3EF)
                )
            ) {
                Text(
                    "INICIAR SESIÓN",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔘 CREAR CUENTA
            OutlinedButton(
                onClick = {
                    navController.navigate("register")
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("CREAR CUENTA")
            }
        }
    }
}