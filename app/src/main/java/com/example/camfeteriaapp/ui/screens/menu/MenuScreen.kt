package com.example.camfeteriaapp.ui.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.R
import androidx.compose.material.icons.filled.Search


@Composable
fun MenuScreen(
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {}
) {

    val background = Brush.Companion.verticalGradient(
        colors = listOf(
            Color(0xFF1A2A44),
            Color(0xFF2F5D8C)
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1A2A44)) {

                NavigationBarItem(
                    selected = true,
                    onClick = onMenuClick,
                    icon = { Icon(Icons.Default.RestaurantMenu, contentDescription = "Menú") },
                    label = { Text("Menú") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onSearchClick,
                    icon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    label = {
                        Text("Buscar")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onCartClick,
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
                    label = { Text("Carrito") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onAccountClick,
                    icon = { Icon(Icons.Default.Person, contentDescription = "Cuenta") },
                    label = { Text("Cuenta") }
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(background)
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Menú",
                fontSize = 26.sp,
                color = Color.Companion.White,
                fontWeight = FontWeight.Companion.SemiBold,
                modifier = Modifier.Companion.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(menuItems.size) { index ->
                    val item = menuItems[index]

                    CategoryCard(
                        title = item.title,
                        image = item.image,
                        onClick = { onCategoryClick(item.title) }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    image: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.Companion
            .height(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = title,
            contentScale = ContentScale.Companion.Crop,
            modifier = Modifier.Companion.fillMaxSize()
        )

        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(Color.Companion.Black.copy(alpha = 0.35f))
        )

        Text(
            text = title,
            color = Color.Companion.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Companion.SemiBold,
            modifier = Modifier.Companion
                .align(Alignment.Companion.Center)
        )
    }
}

data class MenuItem(
    val title: String,
    val image: Int
)

val menuItems = listOf(
    MenuItem("Desayunos", R.drawable.desayuno),
    MenuItem("Entradas", R.drawable.entradas),
    MenuItem("Comidas", R.drawable.comidas),
    MenuItem("Postres", R.drawable.postres),
    MenuItem("Bebidas calientes", R.drawable.bebidascalientes),
    MenuItem("Bebidas frías", R.drawable.bebidasfrias),
    MenuItem("Carrito", R.drawable.carrito),
    MenuItem("Cuenta", R.drawable.cuenta)
)