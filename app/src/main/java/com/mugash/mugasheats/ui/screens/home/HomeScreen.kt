package com.mugash.mugasheats.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R // Adjust to your package

const val ROUT_RESTAURANTMENU = "menu_screen" // Change to your menu route

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Title
        Text(
            text = "Mugash Eats",
            fontSize = 28.sp,
            color = Color(0xFF8B0000), // Deep Red for restaurant vibe
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Banner Image
        Image(
            painter = painterResource(R.drawable.restaurant_banner), // Make sure you have a food-related banner
            contentDescription = "Restaurant Banner",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tagline
        Text(
            text = "Delicious Meals, Delivered Fresh!",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Short Description
        Text(
            text = "Explore our mouth-watering dishes made with love. " +
                    "From sizzling grills to fresh salads, Mugash Eats brings the finest dining experience right to your doorstep.",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // CTA Button
        Button(
            onClick = {
                navController.navigate(ROUT_RESTAURANTMENU)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFF5722)) // Warm orange, food-friendly
        ) {
            Text(
                text = "Browse Menu",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
