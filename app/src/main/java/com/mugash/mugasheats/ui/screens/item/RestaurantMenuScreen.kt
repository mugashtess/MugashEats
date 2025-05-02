package com.mugash.mugasheats.ui.screens.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R

// Define your color here or import from your theme
val newPurple = Color(0xFF9C27B0) // Example purple color

const val ROUT_INTENT = "intent_screen" // Dummy route name for navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenuScreen(navController: NavController) {
    val menuItems = listOf(
        MenuItem(
            name = "Grilled Chicken",
            description = "Juicy grilled chicken with spices",
            oldPrice = "Ksh.1200",
            newPrice = "Ksh.950",
            imageRes = R.drawable.grilled_chicken
        ),
        MenuItem(
            name = "Veggie Pizza",
            description = "Cheesy pizza with fresh veggies",
            oldPrice = "Ksh.1500",
            newPrice = "Ksh.1200",
            imageRes = R.drawable.veggie__pizza
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "Our Menu") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = newPurple,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
                IconButton(onClick = {
                    navController.navigate(ROUT_INTENT)
                }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
                }
            }
        )

        // Body
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            // Banner image
            Image(
                painter = painterResource(R.drawable.restaurant_banner),
                contentDescription = "Restaurant Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Search Bar
            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                },
                placeholder = { Text(text = "Search dishes...") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Menu Items
            menuItems.filter {
                it.name.contains(search, ignoreCase = true)
            }.forEach { item ->
                MenuItemRow(item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MenuItemRow(item: MenuItem) {
    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = item.description, fontSize = 15.sp)
            Text(
                text = item.oldPrice,
                fontSize = 15.sp,
                textDecoration = TextDecoration.LineThrough
            )
            Text(text = "Price : ${item.newPrice}", fontSize = 15.sp)
            Row {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = newPurple
                    )
                }
            }
        }
    }
}

data class MenuItem(
    val name: String,
    val description: String,
    val oldPrice: String,
    val newPrice: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun RestaurantMenuScreenPreview() {
    RestaurantMenuScreen(navController = rememberNavController())
}
