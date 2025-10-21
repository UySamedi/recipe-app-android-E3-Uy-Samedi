package com.example.myapp.ui.srceen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Data.FavoritesRepository
import com.example.myapp.Data.Model.Category
import com.example.myapp.Data.Model.Meal
import com.example.myapp.Data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val userName: String = "Alena Sabyan",
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val featuredRecipes: List<Meal> = emptyList(),
    val popularRecipes: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

open class HomeViewModel(
    private val recipeRepository: RecipeRepository = RecipeRepository(),
    private val favoritesRepository: FavoritesRepository = FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var allMeals: List<Meal> = emptyList()

    init {
        loadInitialData()
        observeFavorites()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val categories = recipeRepository.getCategories()
                val meals = recipeRepository.getMeals()
                allMeals = meals

                val selectedCategory = categories.firstOrNull()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        categories = categories,
                        featuredRecipes = meals.take(5),
                        selectedCategory = selectedCategory,
                        popularRecipes = meals.filter { meal -> meal.category == selectedCategory?.name }
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Failed to load data: ${e.message}") }
            }
        }
    }

    fun selectCategory(category: Category) {
        val filteredMeals = allMeals.filter { it.category == category.name }
        _uiState.update {
            it.copy(
                selectedCategory = category,
                popularRecipes = filteredMeals
            )
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoritesRepository.getFavorites().collect { favoriteMeals ->
                _uiState.update { currentState ->
                    val updatedFeatured = currentState.featuredRecipes.map { meal ->
                        meal.copy(isFavorite = favoriteMeals.any { it.id == meal.id })
                    }
                    val updatedPopular = currentState.popularRecipes.map { meal ->
                        meal.copy(isFavorite = favoriteMeals.any { it.id == meal.id })
                    }
                    currentState.copy(
                        featuredRecipes = updatedFeatured,
                        popularRecipes = updatedPopular
                    )
                }
            }
        }
    }

    fun toggleFavorite(meal: Meal) {
        favoritesRepository.toggleFavorite(meal)
    }
}