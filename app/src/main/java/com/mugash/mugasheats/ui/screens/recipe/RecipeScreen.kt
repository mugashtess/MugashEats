package com.mugash.mugasheats.ui.screens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R // make sure this matches your package

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top AppBar
        TopAppBar(
            title = { Text(text = "Tasty Recipes") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFB71C1C), // Fresh Green for food vibe
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { /* Menu */ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { /* Favorites */ }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // SearchBar
        var search by remember { mutableStateOf("") }
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Search Recipes...") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.recipe_banner), // Ensure this drawable exists
                contentDescription = "Recipes Banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Cook Delicious Meals!",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Popular Recipes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Recipes Row
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            RecipeItem(
                name = "Spaghetti Bolognese",
                time = "30 min",
                imageRes = R.drawable.spaghetti // ensure drawable spaghetti exists
            ) {
                // Navigate to recipe details screen
                navController.navigate("recipe_details/spaghetti_bolognese")
            }

            Spacer(modifier = Modifier.width(16.dp))

            RecipeItem(
                name = "Chicken Curry",
                time = "40 min",
                imageRes = R.drawable.chicken_curry // ensure drawable chicken_curry exists
            ) {
                navController.navigate("recipe_details/chicken_curry")
            }
        }
    }
}

@Composable
fun RecipeItem(name: String, time: String, imageRes: Int, onCookNowClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Time: $time", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onCookNowClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFB71C1C)) // Green for cooking action
        ) {
            Text(text = "Cook Now", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(navController = rememberNavController())
}
