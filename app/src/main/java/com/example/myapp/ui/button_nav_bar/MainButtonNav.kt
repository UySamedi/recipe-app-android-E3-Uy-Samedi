package com.example.myapp.ui.button_nav_bar

import com.example.recipe.ui.navigation.AppBottomNavigationBar
import com.example.recipe.ui.navigation.BottomNavItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.srceen.FavoritesScreen.FavoritesScreen
import com.example.myapp.ui.srceen.HomeScreen.HomeScreen
import com.example.myapp.ui.srceen.SearchScreen.SearchScreen

@Composable
fun MainButtonNav(mainNavController: NavController) {
    val bottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = { AppBottomNavigationBar(navController = bottomBarNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(navController = mainNavController)
            }
            composable(BottomNavItem.Search.route) {
                SearchScreen(navController = mainNavController)
            }
            composable(BottomNavItem.Favorites.route) {
                FavoritesScreen(navController = mainNavController)}
        }
        }

}

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Search Screen")
    }
}

// ✨ FIX: Replace TODO() with a simple UI to prevent crashing
@Composable
fun FavoritesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Favorites Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenViewPreview() {
    val dummyNavController = rememberNavController()
    MainButtonNav(mainNavController = dummyNavController)
}