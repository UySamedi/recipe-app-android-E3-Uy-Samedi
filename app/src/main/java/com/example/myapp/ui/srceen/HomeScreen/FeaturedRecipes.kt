package com.example.myapp.ui.srceen.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapp.Data.Model.Meal
import com.example.myapp.ui.theme.MyappTheme


@Composable
fun FeaturedRecipes(
    recipes: List<Meal>,
    navController: NavController,
    onFavoriteClick: (Meal) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipes) { recipe ->
            FeaturedRecipeCard(
                recipe = recipe,
                onClick = { navController.navigate("mealDetail/${recipe.id}") }
            )
        }
    }
}
@Composable
fun FeaturedRecipeCard(
    recipe: Meal,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(280.dp).clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.height(180.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(recipe.imageUrl).crossfade(true).build(),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeaturedRecipesPreview() {
    val sampleRecipes = listOf(
        Meal(id = "1", name = "Classic Spaghetti Carbonara", imageUrl = "", category = "Pasta"),
        Meal(id = "2", name = "Spicy Thai Green Curry", imageUrl = "", category = "Curry")
    )
    MyappTheme {
        FeaturedRecipes(
            recipes = sampleRecipes,
            navController = rememberNavController(),
            onFavoriteClick = {}
        )
    }
}