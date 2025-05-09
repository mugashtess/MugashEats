package com.mugash.mugasheats.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mugash.mugasheats.model.UserDatabase
import com.mugash.mugasheats.repository.UserRepository
import com.mugash.mugasheats.ui.screens.about.AboutScreen
import com.mugash.mugasheats.ui.screens.auth.LoginScreen
import com.mugash.mugasheats.ui.screens.auth.RegisterScreen
import com.mugash.mugasheats.ui.screens.contact.ContactScreen
import com.mugash.mugasheats.ui.screens.home.HomeScreen
import com.mugash.mugasheats.ui.screens.item.RestaurantMenuScreen
import com.mugash.mugasheats.ui.screens.more.MoreScreen
import com.mugash.mugasheats.ui.screens.recipe.AddRecipeScreen
import com.mugash.mugasheats.ui.screens.recipe.EditRecipeScreen

import com.mugash.mugasheats.ui.screens.recipe.RecipeListScreen
import com.mugash.mugasheats.ui.screens.recipe.RecipeScreen
import com.mugash.mugasheats.ui.screens.splash.SplashScreen
import com.mugash.mugasheats.ui.screens.start.StartScreen
import com.mugash.mugasheats.viewmodel.AuthViewModel
import com.mugash.mugasheats.viewmodel.RecipeViewModel
import com.mugash.sokomart.ui.screens.dashboard.DashboardScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val context = LocalContext.current
    val recipeViewModel: RecipeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Standard Screens
        composable(ROUT_HOME) { HomeScreen(navController) }
        composable(ROUT_ABOUT) { AboutScreen(navController) }
        composable(ROUT_SPLASH) { SplashScreen(navController) }
        composable(ROUT_START) { StartScreen(navController) }
        composable(ROUT_CONTACT) { ContactScreen(navController) }
        composable(ROUT_DASHBOARD) { DashboardScreen(navController) }
        composable(ROUT_MORE) { MoreScreen(navController) }
        composable(ROUT_RESTAURANTMENU) { RestaurantMenuScreen(navController) }
        composable(ROUT_RECIPE) { RecipeScreen(navController) }

        // ✅ Recipe Screens
        composable(ROUT_RECIPE_LIST) {
            RecipeListScreen(navController = navController, viewModel = recipeViewModel)
        }

        composable(ROUT_ADD_RECIPE) {
            AddRecipeScreen(navController = navController, viewModel = recipeViewModel)
        }

        composable(
            route = ROUT_EDIT_RECIPE + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            EditRecipeScreen(recipeId = recipeId.toString(), navController = navController, viewModel = recipeViewModel)
        }

        // ✅ Auth Screens
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
