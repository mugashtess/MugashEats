package com.mugash.mugasheats.ui.screens.start

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.R
import com.mugash.mugasheats.navigation.ROUT_HOME
import com.mugash.mugasheats.navigation.ROUT_REGISTER
import com.mugash.mugasheats.navigation.ROUT_SPLASH
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StartScreen(navController: NavController) {
    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        delay(2000)
        navController.navigate(ROUT_REGISTER
        )

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Mugash Eats!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD84315) // Restaurant theme orange
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.restaurant_logo), // Ensure you have this drawable
            contentDescription = "Restaurant Illustration",
            modifier = Modifier
                .size(280.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Delicious Meals at Your Fingertips",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF6D4C41) // Warm brown
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Manage your restaurant orders, menu, and customers easily — all from one place. Mugash Eats simplifies daily operations so you can focus on serving great food.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { navController.navigate(ROUT_HOME) },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF7043), // Vibrant orange
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(50.dp)
        ) {
            Text(text = "Get Started", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    StartScreen(navController = rememberNavController())
}
