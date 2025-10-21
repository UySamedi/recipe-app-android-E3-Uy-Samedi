package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.ui.button_nav_bar.MainButtonNav
import com.example.myapp.ui.button_nav_bar.navigation.AppRoutes
import com.example.myapp.ui.srceen.CategoryDetail.CategoryDetailScreen
import com.example.myapp.ui.srceen.MealDetail.MealDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.MAIN_SCREEN
    ) {
        composable(AppRoutes.MAIN_SCREEN) {
            MainButtonNav(mainNavController = navController)
        }

        composable(
            route = AppRoutes.MEAL_DETAIL,
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            requireNotNull(mealId) { "Meal ID is required" }
            MealDetailScreen(navController = navController, mealId = mealId)
        }

        composable(
            route = AppRoutes.CATEGORY_DETAIL,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            requireNotNull(categoryId) { "Category ID is required" }
            CategoryDetailScreen(navController = navController, categoryId = categoryId)
        }
    }
}