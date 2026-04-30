package com.example.camfeteriaapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.R
import kotlinx.coroutines.delay
import kotlin.math.sin

@Composable
fun SplashScreen(onFinish: () -> Unit) {

    // Timer para pasar a otra pantalla
    LaunchedEffect(Unit) {
        // animación entra
        delay(1200)

        // pausa visual (branding)
        delay(2500)

        onFinish()
    }

    // Animación infinita (onda)
    val infiniteTransition = rememberInfiniteTransition()

    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing)
        )
    )

    // Animación logo
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1200, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1200)
    )

    // Animación texto
    var showText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1200)
        showText = true
    }

    val textAlpha by animateFloatAsState(
        targetValue = if (showText) 1f else 0f,
        animationSpec = tween(1000)
    )

    // Fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A2A44),
                        Color(0xFF2F5D8C)
                    )
                )
            )
    ) {

        // Onda animada REAL
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
        ) {
            val path = Path()
            val waveHeight = 40f
            val width = size.width
            val height = size.height

            path.moveTo(0f, height * 0.5f)

            for (x in 0..width.toInt()) {
                val y = (sin((x + waveOffset) * 0.02f) * waveHeight + height * 0.5f).toFloat()
                path.lineTo(x.toFloat(), y)
            }

            path.lineTo(width, height)
            path.lineTo(0f, height)
            path.close()

            drawPath(
                path = path,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD9A066).copy(alpha = 0.25f),
                        Color(0xFFA47148).copy(alpha = 0.20f),
                        Color(0xFF6F4E37).copy(alpha = 0.15f)
                    )
                )
            )
        }

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo animado
            Image(
                painter = painterResource(id = R.drawable.logocafeteria),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Texto animado
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(textAlpha)
            ) {

                Text(
                    text = "CAMensales",
                    fontSize = 18.sp,
                    color = Color(0xFFF5F3EF)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "\"Calidad con calidez\"",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFFE7E3DC)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Nos esforzamos por atenderlo\ncomo se merece",
                    fontSize = 13.sp,
                    color = Color(0xFFE7E3DC),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Logos con opacidad
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.escudounam),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0.35f)
                )

                Image(
                    painter = painterResource(id = R.drawable.escudofi),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0.35f)
                )
            }
        }
    }
}