package com.example.myapp.Data

import com.example.myapp.Data.Model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FavoritesRepository {

    private val _favoriteMeals = MutableStateFlow<List<Meal>>(emptyList())

    fun getFavorites(): StateFlow<List<Meal>> = _favoriteMeals.asStateFlow()

    fun toggleFavorite(meal: Meal) {
        _favoriteMeals.update { currentFavorites ->
            val isAlreadyFavorite = currentFavorites.any { it.id == meal.id }
            if (isAlreadyFavorite) {
                currentFavorites.filterNot { it.id == meal.id }
            } else {
                currentFavorites + meal.copy(isFavorite = true)
            }
        }
    }
}