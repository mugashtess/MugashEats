package com.mugash.sokomart.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R
import com.mugash.mugasheats.navigation.ROUT_ABOUT
import com.mugash.mugasheats.navigation.ROUT_HOME
import com.mugash.mugasheats.navigation.ROUT_ITEM

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Card - Restaurant Branding
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Yellow)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.splash), // e.g., restaurant logo
                    contentDescription = "Restaurant Logo",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = "Mugash Eats",
                    fontSize = 34.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // First Row: Menu & Orders
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            DashboardCard(
                iconRes = R.drawable.itemicon,
                label = "Menu",
                onClick = { navController.navigate(ROUT_ITEM) }
            )

            DashboardCard(
                iconRes = R.drawable.splash,
                label = "Orders",
                onClick = { navController.navigate(ROUT_HOME) }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Second Row: About & More
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            DashboardCard(
                iconRes = R.drawable.abouticon,
                label = "About Us",
                onClick = { navController.navigate(ROUT_ABOUT) }
            )

            DashboardCard(
                iconRes = R.drawable.moreicon,
                label = "More",
                onClick = { navController.navigate(ROUT_MORE) }
            )
        }
    }
}

@Composable
fun DashboardCard(
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(180.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = newPurple),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}
