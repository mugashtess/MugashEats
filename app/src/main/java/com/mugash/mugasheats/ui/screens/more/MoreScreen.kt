package com.mugash.mugasheats.ui.screens.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val mContext = LocalContext.current

        // TopAppBar
        TopAppBar(
            title = { Text(text = "More Dishes") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFF5722), // Warm Orange for food app
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { /* Cart Click */ }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                }
            }
        )
        // End of TopAppBar

        Spacer(modifier = Modifier.height(20.dp))

        // SearchBar
        var search by remember { mutableStateOf("") }
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Search Dishes...") }
        )
        // End Of SearchBar

        Spacer(modifier = Modifier.height(20.dp))

        // Banner Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.restaurant_banner), // Make sure you have a restaurant banner image
                contentDescription = "Restaurant",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(20.dp)
            )
            Text(
                text = "Discover Tasty Meals!",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        // End of Banner Box

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Popular Dishes",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Popular Dishes Row
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            DishItem(
                name = "Grilled Chicken",
                price = "Ksh.950",
                imageRes = R.drawable.grilled_chicken
            ) {
                // Simulate Payment action (use actual order flow in real app)
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            }

            Spacer(modifier = Modifier.width(10.dp))

            DishItem(
                name = "Veggie Pizza",
                price = "Ksh.1200",
                imageRes = R.drawable.veggie__pizza
            ) {
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            }
        }
        // End of Row
    }
}

@Composable
fun DishItem(name: String, price: String, imageRes: Int, onPayClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(150.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = price, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onPayClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)) // Orange for food button
        ) {
            Text(text = "Order Now", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen(navController = rememberNavController())
}
