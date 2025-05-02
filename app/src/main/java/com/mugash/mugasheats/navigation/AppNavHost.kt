package com.mugash.mugasheats.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mugash.mugasheats.model.UserDatabase
import com.mugash.mugasheats.repository.UserRepository
import com.mugash.mugasheats.ui.screens.about.AboutScreen
import com.mugash.mugasheats.ui.screens.auth.LoginScreen
import com.mugash.mugasheats.ui.screens.auth.RegisterScreen
import com.mugash.mugasheats.ui.screens.contact.ContactScreen
import com.mugash.mugasheats.ui.screens.home.HomeScreen
import com.mugash.mugasheats.ui.screens.item.RestaurantMenuScreen
import com.mugash.mugasheats.ui.screens.more.MoreScreen
import com.mugash.mugasheats.ui.screens.recipe.RecipeScreen
import com.mugash.mugasheats.ui.screens.splash.SplashScreen
import com.mugash.mugasheats.ui.screens.start.StartScreen
import com.mugash.mugasheats.viewmodel.AuthViewModel
import com.mugash.sokomart.ui.screens.dashboard.DashboardScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH


) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_START) {
            StartScreen(navController)
        }
        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }
        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }
        composable(ROUT_MORE) {
            MoreScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_RESTAURANTMENU) {
            RestaurantMenuScreen(navController)
        }
        composable(ROUT_RECIPE) {
            RecipeScreen(navController)
        }













        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }



    }
}